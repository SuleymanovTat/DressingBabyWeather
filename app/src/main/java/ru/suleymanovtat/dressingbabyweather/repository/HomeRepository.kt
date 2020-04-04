package ru.suleymanovtat.dressingbabyweather.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import ru.suleymanovtat.dressingbabyweather.BuildConfig
import ru.suleymanovtat.dressingbabyweather.model.database.Weather
import ru.suleymanovtat.dressingbabyweather.model.database.mapToLocal
import ru.suleymanovtat.dressingbabyweather.model.local.*
import ru.suleymanovtat.dressingbabyweather.presentation.home.adapter.DiffItem
import ru.suleymanovtat.dressingbabyweather.repository.database.AppDatabase
import ru.suleymanovtat.dressingbabyweather.repository.network.ServerCommunicator
import ru.suleymanovtat.dressingbabyweather.utils.handleErrors
import java.util.*

class Result(
    var status: Int = 1,
    var error: Exception? = null
)

class HomeRepository(
    private val serverCommunicator: ServerCommunicator,
    private val database: AppDatabase
) {

    private fun getAge(date: Long): Int {
        val endDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        endDate.timeInMillis = date
        val startDate1 = Calendar.getInstance()
        startDate1.set(
            startDate.get(Calendar.YEAR),
            startDate.get(Calendar.MONTH) + 1,
            startDate.get(Calendar.DAY_OF_MONTH)
        )
        val endDate1 = Calendar.getInstance()
        endDate1.set(
            endDate.get(Calendar.YEAR),
            endDate.get(Calendar.MONTH) + 1,
            endDate.get(Calendar.DAY_OF_MONTH)
        )
        val dateCaculator = DateCalculator.calculateAge(startDate1, endDate1)
        return dateCaculator.year
    }

    fun getDirectory(): Flow<List<DressLocal>> {
        return database.settingsDao().getSettingsFlow().handleErrors()
            .map {
                var age = getAge(it?.date ?: 0)
                if (age > 7) age = 7
                database.dressDao().getDressFindByAge(age).map { it.mapToLocal() }
            }
    }

    @ExperimentalCoroutinesApi
    suspend fun getList(): Flow<ArrayList<DiffItem>>? {
        return database.settingsDao().getSettingsFlow()
            .combine(database.weatherDao().getWeatherFlow().map { it ->
                if (it?.name == null) {
                    it?.name = ""
                    it?.description = ""
                    it?.temp = ""
                    it?.image = ""
                }
                it
            }) { a, weather ->
                val items = arrayListOf<DiffItem>()
                if (weather != null) {
                    items.add(
                        WeatherLocal(
                            1,
                            weather.name!!,
                            weather.description!!,
                            weather.temp!!.toDouble().toInt().toString(),
                            weather.image!!
                        )
                    )
                    items.add(
                        Cards(
                            "2",
                            listCard(
                                weather.temp!!.toDouble().toInt(),
                                getAge(database.settingsDao().getSettings().date)
                            )
                        )
                    )
                }
                items
            }
    }


    fun listCard(it: Int, year: Int): List<CardLocal> {
        return when (year) {
            0, 1 -> database.weatherDressDao().getDress(
                0,
                it
            ).map { it.mapToLocal() }
            else -> arrayListOf<CardLocal>()
        }
    }

    suspend fun getWeather(): Result {
        val settings = database.settingsDao().getSettings()
        if (settings.lat != null) {
            try {
                val weatherBase = serverCommunicator.getWeather(
                    settings.lat!!,
                    settings.lng!!,
                    BuildConfig.API_KEY
                )
                val weather = weatherBase.weather?.first()
                database.weatherDao().insertWeather(
                    Weather(
                        1, weatherBase.name, weather?.description?.capitalize(),
                        weatherBase.main?.temp.toString().capitalize(), weather?.icon
                    )
                )
                return Result(1)
            } catch (ex: Exception) {
                return Result(0, ex)
            }
        }
        return Result()
    }
}