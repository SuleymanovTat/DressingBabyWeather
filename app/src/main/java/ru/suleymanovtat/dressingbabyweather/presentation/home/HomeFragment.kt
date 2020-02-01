package ru.suleymanovtat.dressingbabyweather.presentation.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_home.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.di.component.ViewModelComponent
import ru.suleymanovtat.dressingbabyweather.model.local.CardLocal
import ru.suleymanovtat.dressingbabyweather.presentation.base.BaseFragment
import ru.suleymanovtat.dressingbabyweather.presentation.home.adapter.DressAdapter
import ru.suleymanovtat.dressingbabyweather.presentation.home.adapter.HomeAdapter
import javax.inject.Inject


class HomeFragment : BaseFragment(R.layout.fragment_home), DressAdapter.OnCardClickListener {

    var homeViewModel: HomeViewModel? = null
        @Inject set

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sendAnalytics("HomeFragment")
        homeViewModel?.dashboards?.observe(this, Observer { items ->
            recyclerViewDesc.adapter = HomeAdapter(items, this)
            hideRefreshProgressBar()
        })
        refreshLayout.setOnRefreshListener {
            showRefreshProgressBar()
            homeViewModel?.loading()
        }
    }

    fun showRefreshProgressBar() {
        with(refreshLayout) {
            if (!isRefreshing) {
                isEnabled = false
                isRefreshing = true
            }
        }
    }

    fun hideRefreshProgressBar() {
        with(refreshLayout) {
            if (isRefreshing) {
                isRefreshing = false
                isEnabled = true
            }
        }
    }

    override fun onCardLocal(cardLocal: CardLocal) {
        val mBundle = Bundle().apply { putParcelable("card", cardLocal) }
        Navigation.findNavController(view!!).navigate(R.id.mainDetailsFragment, mBundle)
    }
}


