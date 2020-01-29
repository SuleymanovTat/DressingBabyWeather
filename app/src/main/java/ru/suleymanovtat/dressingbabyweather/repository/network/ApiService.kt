package ru.suleymanovtat.dressingbabyweather.repository.network

import retrofit2.http.*
import ru.suleymanovtat.dressingbabyweather.model.network.WeatherBase

interface ApiService {

    @GET("weather?units=metric&lang=ru")
    suspend fun getWeather(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appid") appid: String): WeatherBase
}
