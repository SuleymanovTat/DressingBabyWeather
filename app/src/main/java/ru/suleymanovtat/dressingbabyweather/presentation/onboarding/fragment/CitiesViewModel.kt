package ru.suleymanovtat.dressingbabyweather.presentation.onboarding.fragment

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.suleymanovtat.dressingbabyweather.di.module.BaseViewModel
import ru.suleymanovtat.dressingbabyweather.domain.CitiesInteractor
import ru.suleymanovtat.dressingbabyweather.model.local.CityLocal

class CitiesViewModel(
    application: Application,
    val citiesInteractor: CitiesInteractor
) : BaseViewModel(application) {

    val listCity = MutableLiveData<List<CityLocal>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            listCity.postValue(citiesInteractor.getCities())
        }
    }

    fun saveCity(city: CityLocal) {
        listCity.value = listCity.value!!.map { it ->
            it.selected = false
            if (it.id == city.id) {
                it.selected = true
            }
            it
        }
        viewModelScope.launch(Dispatchers.IO) {
            citiesInteractor.saveCity(city)
        }
    }
}