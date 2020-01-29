package ru.suleymanovtat.dressingbabyweather.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.suleymanovtat.dressingbabyweather.model.database.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: Weather)

    @Query("SELECT * from weather_table LIMIT 1")
    fun getWeatherFlow(): Flow<Weather?>
}