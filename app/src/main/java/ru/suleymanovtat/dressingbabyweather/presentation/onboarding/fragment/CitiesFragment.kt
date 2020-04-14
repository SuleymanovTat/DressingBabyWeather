package ru.suleymanovtat.dressingbabyweather.presentation.onboarding.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.city_fragment.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.di.component.ViewModelComponent
import ru.suleymanovtat.dressingbabyweather.model.local.CityLocal
import ru.suleymanovtat.dressingbabyweather.presentation.base.BaseFragment
import ru.suleymanovtat.dressingbabyweather.presentation.onboarding.adapter.CityAdapter
import javax.inject.Inject


class CitiesFragment : BaseFragment(R.layout.city_fragment),
    CityAdapter.OnCityClickListener {

    companion object {
        fun newInstance() =
            CitiesFragment()
    }

    var viewModel: CitiesViewModel? = null
        @Inject set

    override fun injectDependency(component: ViewModelComponent) = component.inject(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val cityAdapter = CityAdapter(arrayListOf(), this)
        recyclerViewCity.adapter = cityAdapter
        viewModel?.listCity?.observe(viewLifecycleOwner, Observer { newItems ->
//            cityAdapter.items = cities
//            cityAdapter.notifyDataSetChanged()
//            val authorDiffUtilCallback = CityDiffUtilCallback(cityAdapter.items, newItems)
//            val authorDiffResult = DiffUtil.calculateDiff(authorDiffUtilCallback)
//            cityAdapter.items = newItems as MutableList<CityLocal>
//            authorDiffResult.dispatchUpdatesTo(cityAdapter)
            cityAdapter.updateEmployeeListItems(newItems)
        })
        tvNoMyCity.setOnClickListener { sendMessage() }
    }

    override fun onCityClick(city: CityLocal) {
        viewModel?.saveCity(city)
    }
}
