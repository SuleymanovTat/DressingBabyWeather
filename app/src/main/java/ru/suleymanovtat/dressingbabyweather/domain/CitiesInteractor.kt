package ru.suleymanovtat.dressingbabyweather.domain

import ru.suleymanovtat.dressingbabyweather.model.local.CityLocal
import ru.suleymanovtat.dressingbabyweather.repository.CitiesRepository

class CitiesInteractor(val repository: CitiesRepository) {

    suspend fun getCities() = repository.getCities()
    fun getCitiesFlow() = repository.getCitiesFlow()
    fun saveCity(city: CityLocal) = repository.saveCity(city)
    suspend fun getWeather() = repository.getWeather()
    fun loadingCities() = repository.loadingCities()
}