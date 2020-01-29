package ru.suleymanovtat.dressingbabyweather.presentation.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.di.component.ViewModelComponent
import ru.suleymanovtat.dressingbabyweather.presentation.base.BaseActivity
import ru.suleymanovtat.dressingbabyweather.utils.setupWithNavController
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    private var currentNavController: LiveData<NavController>? = null
    var viewModel: MainViewModel? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun injectDependency(component: ViewModelComponent) = component.inject(this)


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState!!)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navGraphIds =
            listOf(
                R.navigation.navigation_main,
                R.navigation.navigation_dashboard,
                R.navigation.navigation_settings
            )
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )
        currentNavController = controller
    }

    override fun onSupportNavigateUp() = currentNavController?.value?.navigateUp() ?: false

}
