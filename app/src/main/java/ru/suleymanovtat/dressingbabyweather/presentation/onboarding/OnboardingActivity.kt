package ru.suleymanovtat.dressingbabyweather.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_onboarding.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.di.component.ViewModelComponent
import ru.suleymanovtat.dressingbabyweather.presentation.base.BaseActivity
import ru.suleymanovtat.dressingbabyweather.presentation.main.MainActivity
import ru.suleymanovtat.dressingbabyweather.presentation.onboarding.adapter.ViewPagerFragmentAdapter
import ru.suleymanovtat.dressingbabyweather.utils.PagerIndicatorView
import ru.suleymanovtat.dressingbabyweather.utils.ViewPager2PagerListener
import javax.inject.Inject


class OnboardingActivity : BaseActivity(R.layout.activity_onboarding) {


    var viewModel: OnboardingViewModel? = null
        @Inject set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel?.isLogged?.observe(this, Observer { isLogged ->
            if (isLogged) startMainActivity()
        })
        btnNext.setOnClickListener {
            val current = viewPager.currentItem + 1
            if (current < 3) {
                viewPager.currentItem = current
            } else {
                viewModel?.isLogged(true)
                startMainActivity()
            }
        }
        viewPager.adapter = ViewPagerFragmentAdapter(supportFragmentManager, lifecycle)
        val indicator: PagerIndicatorView = findViewById(R.id.indicator)
        indicator.setPager(ViewPager2PagerListener(viewPager))
    }

    override fun injectDependency(component: ViewModelComponent) = component.inject(this)

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
