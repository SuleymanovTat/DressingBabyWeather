package ru.suleymanovtat.dressingbabyweather.presentation.onboarding.fragment

import android.os.Bundle
import kotlinx.android.synthetic.main.date_of_birth_fragment.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.di.component.ViewModelComponent
import ru.suleymanovtat.dressingbabyweather.presentation.base.BaseFragment
import java.util.Calendar.*
import javax.inject.Inject

class DateOfBirthFragment : BaseFragment(R.layout.date_of_birth_fragment) {

    companion object {
        fun newInstance() =
            DateOfBirthFragment()
    }

    var viewModel: DateOfBirthViewModel? = null
        @Inject set

    override fun injectDependency(component: ViewModelComponent) = component.inject(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val calendar = getInstance()
        datePicker.maxDate = System.currentTimeMillis()
        datePicker.minDate = 631141200000
        datePicker.init(
            calendar.get(YEAR),
            calendar.get(MONTH),
            calendar.get(DAY_OF_MONTH)
        ) { _, year, monthOfYear, dayOfMonth ->
            viewModel?.saveDate(year, monthOfYear, dayOfMonth)
        }
    }
}
