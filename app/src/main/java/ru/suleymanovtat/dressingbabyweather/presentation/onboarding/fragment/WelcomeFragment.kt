package ru.suleymanovtat.dressingbabyweather.presentation.onboarding.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.welcome_fragment.*
import ru.suleymanovtat.dressingbabyweather.BuildConfig
import ru.suleymanovtat.dressingbabyweather.R

class WelcomeFragment : Fragment(R.layout.welcome_fragment) {

    companion object {
        fun newInstance() =
            WelcomeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvPrivacyPolicy.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(BuildConfig.PRIVACY_POLICY_LINK)
            startActivity(intent)
        }
    }
}
