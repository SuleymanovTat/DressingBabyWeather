package ru.suleymanovtat.dressingbabyweather.presentation.city

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.list_city_fragment.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.di.component.ViewModelComponent
import ru.suleymanovtat.dressingbabyweather.model.local.CityLocal
import ru.suleymanovtat.dressingbabyweather.presentation.base.BaseFragment
import javax.inject.Inject


class ListCityFragment : Baseragment(R.layout.list_city_fragment),
    ListCityAdapter.OnCityClickListener {

    var viewModel: ListCityViewModel? = null
        @Inject set

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnBack.setOnClickListener { activity?.onBackPressed() }
        viewModel?.listCity?.observe(this, Observer { cities ->
            val cityAdapter = ListCityAdapter(cities, this)
            recyclerViewListCity.adapter = cityAdapter
        })
    }

    override fun onCityClick(city: CityLocal) {
        viewModel?.saveCity(city)
        activity?.onBackPressed()
    }
}
