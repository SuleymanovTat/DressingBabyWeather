package ru.suleymanovtat.dressingbabyweather.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.suleymanovtat.dressingbabyweather.model.local.CardLocal

@Entity(tableName = "weather_dress_table")
data class WeatherDress(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "age")
    var age: Int? = null,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "image")
    var image: String? = null,
    @ColumnInfo(name = "min_temp")
    var min_temp: Int? = null,
    @ColumnInfo(name = "max_temp")
    var max_temp: Int? = null
)

fun WeatherDress.mapToLocal(): CardLocal {
    val model = this
    val cardLocal = CardLocal()
    with(cardLocal) {
        id = model.id.toString()
        description = model.description
        image = model.image
    }
    return cardLocal
}




