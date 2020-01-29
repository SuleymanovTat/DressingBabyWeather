package ru.suleymanovtat.dressingbabyweather.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.suleymanovtat.dressingbabyweather.model.database.WeatherDress

@Dao
interface WeatherDressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeatherDress(dress: List<WeatherDress>)

    @Query("SELECT * FROM weather_dress_table WHERE age = :age AND  min_temp <= :temp AND :temp < max_temp")
    fun getDress(age: Int, temp: Int): List<WeatherDress>
}