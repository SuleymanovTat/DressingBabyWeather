package ru.suleymanovtat.dressingbabyweather.repository

import android.annotation.SuppressLint
import ru.suleymanovtat.dressingbabyweather.BuildConfig
import ru.suleymanovtat.dressingbabyweather.model.database.Weather
import ru.suleymanovtat.dressingbabyweather.model.database.mapToSettings
import ru.suleymanovtat.dressingbabyweather.model.local.CityLocal
import ru.suleymanovtat.dressingbabyweather.model.local.mapToLocal
import ru.suleymanovtat.dressingbabyweather.repository.database.AppDatabase
import ru.suleymanovtat.dressingbabyweather.repository.network.ServerCommunicator


class CitiesRepository(val serverCommunicator: ServerCommunicator, val database: AppDatabase) {

    suspend fun getCities() =
        database.cityDao().getCity().map { it.mapToLocal() }.sortedBy { it -> it.name }

    fun saveCity(city: CityLocal) =
        database.settingsDao().insertOrUpdate(city.mapToSettings())

    @SuppressLint("DefaultLocale")
    suspend fun getWeather() {
        val settings = database.settingsDao().getSettings()
        settings.lat.let {

            val weatherBase =
                serverCommunicator.getWeather(settings.lat!!, settings.lng!!, BuildConfig.API_KEY)
            weatherBase.let {
                val weather = weatherBase.weather?.first()
                database.weatherDao().insertWeather(
                    Weather(
                        1, weatherBase.name, weather?.description?.capitalize(),
                        weatherBase.main?.temp.toString().capitalize(), weather?.icon
                    )
                )
            }
        }
    }
}