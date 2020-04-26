package ru.suleymanovtat.dressingbabyweather.repository

import android.annotation.SuppressLint
import com.crashlytics.android.Crashlytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.suleymanovtat.dressingbabyweather.BuildConfig
import ru.suleymanovtat.dressingbabyweather.model.database.Weather
import ru.suleymanovtat.dressingbabyweather.model.database.mapToSettings
import ru.suleymanovtat.dressingbabyweather.model.local.CityLocal
import ru.suleymanovtat.dressingbabyweather.model.local.mapToLocal
import ru.suleymanovtat.dressingbabyweather.model.network.Cities
import ru.suleymanovtat.dressingbabyweather.repository.database.AppDatabase
import ru.suleymanovtat.dressingbabyweather.repository.network.ServerCommunicator


class CitiesRepository(val serverCommunicator: ServerCommunicator, val database: AppDatabase) {

    suspend fun getCities() =
        database.cityDao().getCity().map { it.mapToLocal() }.sortedBy { it -> it.name }

    fun getCitiesFlow() =
        database.cityDao().getCityFlow().map {
            it.map {
                it.mapToLocal()
            }.sortedBy { city -> city.name }
        }


    fun saveCity(city: CityLocal) =
        database.settingsDao().insertOrUpdate(city.mapToSettings())

    @SuppressLint("DefaultLocale")
    suspend fun getWeather() {
        val settings = database.settingsDao().getSettings()
        settings.lat?.let {
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

    fun loadingCities() {
        val databaseFirebase = Firebase.database
        val myRef = databaseFirebase.getReference("weather_clothes").child("cities")
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Crashlytics.log(error.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                GlobalScope.launch {
                    try {
                        val cities = dataSnapshot.getValue(Cities::class.java)
                        val list = cities?.ru?.cities
                        list?.let {
                            database.cityDao().saveCities(it)
                        }
                    } catch (ex: Exception) {
                        Crashlytics.logException(ex)
                    }
                }
            }
        })
    }
}