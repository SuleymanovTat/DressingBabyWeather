package ru.suleymanovtat.dressingbabyweather.presentation.settings

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.DatePicker
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_settings.*
import ru.suleymanovtat.dressingbabyweather.BuildConfig
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.di.component.ViewModelComponent
import ru.suleymanovtat.dressingbabyweather.model.local.DateCalculator
import ru.suleymanovtat.dressingbabyweather.presentation.base.BaseFragment
import ru.suleymanovtat.dressingbabyweather.presentation.base.DialogAppFragment
import ru.suleymanovtat.dressingbabyweather.presentation.base.DialogAppFragment.Companion.KEY_MESSAGE
import ru.suleymanovtat.dressingbabyweather.presentation.base.DialogAppFragment.Companion.KEY_TITLE
import ru.suleymanovtat.dressingbabyweather.presentation.onboarding.OnboardingActivity
import java.util.*
import javax.inject.Inject


class SettingsFragment : BaseFragment(R.layout.fragment_settings),
    DatePickerDialog.OnDateSetListener {


    var settingsViewModel: SettingsViewModel? = null
        @Inject set


    override fun injectDependency(component: ViewModelComponent) = component.inject(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sendAnalytics("SettingsFragment")
        rlCity.setOnClickListener {
            sendAnalytics("Click city")
            findNavController().navigate(R.id.action_city)
        }
        rlDateBirth.setOnClickListener {
            sendAnalytics("Click date birth")
            showDatePicker()
        }
        tvExit.setOnClickListener {
            sendAnalytics("Click exit")
            val dialog = DialogAppFragment()
            val args = Bundle().apply {
                putString(KEY_TITLE, getString(R.string.exit))
                putString(KEY_MESSAGE, getString(R.string.do_you_really_want_leave))
            }
            dialog.setArguments(args)
            dialog.setTargetFragment(this, 120)
            dialog.show(fragmentManager!!, null)

        }
        settingsViewModel?.settingsLiveData?.observe(this, Observer { settings ->
            tvCity.text = settings.name ?: getString(R.string.enter_a_city)
            tvDateBirth.text = getAge(settings.date)
        })
        tvWriteToDeveloper.setOnClickListener {
            sendAnalytics("Click send message")
            sendMessage() }
        tvPrivacyPolicy.setOnClickListener {
            sendAnalytics("Click privacy policy")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(BuildConfig.PRIVACY_POLICY_LINK)
            startActivity(intent)
        }
    }

    private fun getAge(date: Long): String {
        val endDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        endDate.timeInMillis = date
        val startDate1 = Calendar.getInstance()
        startDate1.set(
            startDate.get(Calendar.YEAR),
            startDate.get(Calendar.MONTH) + 1,
            startDate.get(Calendar.DAY_OF_MONTH)
        )
        val endDate1 = Calendar.getInstance()
        endDate1.set(
            endDate.get(Calendar.YEAR),
            endDate.get(Calendar.MONTH) + 1,
            endDate.get(Calendar.DAY_OF_MONTH)
        )
        val dateCaculator = DateCalculator.calculateAge(startDate1, endDate1)
        return getString(
            R.string.year,
            dateCaculator.year,
            dateCaculator.month,
            dateCaculator.day
        )
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = 0
        calendar.set(year, month, day, 0, 0, 0)
        settingsViewModel?.saveDate(calendar.time.time)
    }

    fun showDatePicker() {
        val cal = Calendar.getInstance()
        cal.timeInMillis = settingsViewModel?.getDate() ?: 0
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(
            activity!!,
            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            this,
            year,
            month,
            day
        )
        dialog.datePicker.minDate = 631141200000
        dialog.datePicker.maxDate = System.currentTimeMillis()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 120 && resultCode == Activity.RESULT_OK) {
            settingsViewModel?.clearStorage()
            val intent = Intent(activity, OnboardingActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            activity?.finish()
        }
    }
}


