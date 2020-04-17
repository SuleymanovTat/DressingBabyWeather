package ru.suleymanovtat.dressingbabyweather.presentation.dashboard

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.di.component.ViewModelComponent
import ru.suleymanovtat.dressingbabyweather.presentation.base.BaseFragment
import javax.inject.Inject

class DashboardFragment : BaseFragment(R.layout.fragment_dashboard) {

    var viewModel: DashboardViewModel? = null
        @Inject set
    val activityScope = CoroutineScope(Dispatchers.Main)


    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sendAnalytics("DashboardFragment")
        viewModel?.dashboards?.observe(viewLifecycleOwner, Observer { items ->
            if (items.isEmpty()) {
                recyclerViewDashboard.visibility = View.GONE
            } else {
                val dashboardAdapter = DashboardAdapter(items)
                recyclerViewDashboard.adapter = dashboardAdapter
                recyclerViewDashboard.visibility = View.VISIBLE
            }
            hideRefreshProgressBar()
        })
        refreshLayout.setOnRefreshListener {
            showRefreshProgressBar()
            activityScope.launch {
                viewModel?.loadingDress()
                delay(800)
                hideRefreshProgressBar()
            }
        }
    }

    fun showRefreshProgressBar() {
        refreshLayout.let {
            with(refreshLayout) {
                if (!isRefreshing) {
                    isEnabled = false
                    isRefreshing = true
                }
            }
        }
    }

    fun hideRefreshProgressBar() {
        if (refreshLayout != null && refreshLayout.isRefreshing) {
            refreshLayout.isRefreshing = false
            refreshLayout.isEnabled = true
        }
    }

    override fun onDestroy() {
        activityScope.cancel()
        super.onDestroy()
    }
}