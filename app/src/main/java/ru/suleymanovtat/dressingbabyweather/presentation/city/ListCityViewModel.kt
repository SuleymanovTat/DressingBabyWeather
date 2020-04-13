package ru.suleymanovtat.dressingbabyweather.presentation.city

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.suleymanovtat.dressingbabyweather.di.module.BaseViewModel
import ru.suleymanovtat.dressingbabyweather.domain.CitiesInteractor
import ru.suleymanovtat.dressingbabyweather.model.local.CityLocal

class ListCityViewModel(application: Application, private val citiesInteractor: CitiesInteractor) :
    BaseViewModel(application) {

    val listCity = MutableLiveData<List<CityLocal>>()

    init {
        loadingCities()
        viewModelScope.launch(Dispatchers.IO) {
            citiesInteractor.getCitiesFlow().collect {
                listCity.postValue(it)
            }
        }
    }

    fun saveCity(city: CityLocal) {
        viewModelScope.launch(Dispatchers.IO) {
            citiesInteractor.saveCity(city)
            citiesInteractor.getWeather()
        }
    }

    fun loadingCities() {
        viewModelScope.launch(Dispatchers.IO) {
            citiesInteractor.loadingCities()
        }
    }
}
