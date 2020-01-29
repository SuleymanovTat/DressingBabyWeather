package ru.suleymanovtat.dressingbabyweather.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.suleymanovtat.dressingbabyweather.model.local.CityLocal

@Entity(tableName = "city_table")
data class City(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "lat") var lat: Double? = null,
    @ColumnInfo(name = "lng") var lng: Double? = null
)

fun CityLocal.mapToRealmModel(): City {
    val model = this
    val cityDb = City()
    with(cityDb) {
        id = model.id
        name = model.name
        lat = model.lat
        lng = model.lng
    }
    return cityDb
}