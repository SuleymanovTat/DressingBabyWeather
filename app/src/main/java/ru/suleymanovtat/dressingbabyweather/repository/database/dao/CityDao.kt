package ru.suleymanovtat.dressingbabyweather.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.suleymanovtat.dressingbabyweather.model.database.City

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCities(settings: List<City>)

    @Query("SELECT * from city_table")
    suspend fun getCity(): List<City>

    @Query("SELECT * FROM city_table WHERE id = :id")
    fun getCityById(id: Int): City

    @Query("SELECT * FROM city_table WHERE id = :id")
    fun getCityByIdL(id: Int): LiveData<City>
}