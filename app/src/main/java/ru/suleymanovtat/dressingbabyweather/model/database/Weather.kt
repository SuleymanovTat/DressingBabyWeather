package ru.suleymanovtat.dressingbabyweather.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weather_table")
data class Weather(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 1,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "temp")
    var temp: String? = null,
    @ColumnInfo(name = "image")
    var image: String? = null)


