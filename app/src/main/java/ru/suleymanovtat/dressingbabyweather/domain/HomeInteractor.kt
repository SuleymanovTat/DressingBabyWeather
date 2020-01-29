package ru.suleymanovtat.dressingbabyweather.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.suleymanovtat.dressingbabyweather.repository.HomeRepository

class HomeInteractor(val repository: HomeRepository) {

    fun getDirectory() = repository.getDirectory()

    @ExperimentalCoroutinesApi
    suspend fun getList() = repository.getList()

    suspend fun getWeather() = repository.getWeather()
}