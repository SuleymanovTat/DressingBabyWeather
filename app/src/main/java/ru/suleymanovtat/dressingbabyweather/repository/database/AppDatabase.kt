package ru.suleymanovtat.dressingbabyweather.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.suleymanovtat.dressingbabyweather.model.database.*
import ru.suleymanovtat.dressingbabyweather.repository.database.dao.*

@Database(
    entities = [Weather::class, City::class, Settings::class, Dress::class, WeatherDress::class],
    version = 11,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDressDao(): WeatherDressDao
    abstract fun weatherDao(): WeatherDao
    abstract fun cityDao(): CityDao
    abstract fun settingsDao(): SettingsDao
    abstract fun dressDao(): DressDao
}