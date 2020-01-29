package ru.suleymanovtat.dressingbabyweather.repository.network

import ru.suleymanovtat.dressingbabyweather.model.network.WeatherBase

class ServerCommunicator(private val apiService: ApiService) {

    suspend fun getWeather(lat: Double, lng: Double, apiKey: String): WeatherBase {
        return apiService.getWeather(lat, lng, apiKey)
    }

}
