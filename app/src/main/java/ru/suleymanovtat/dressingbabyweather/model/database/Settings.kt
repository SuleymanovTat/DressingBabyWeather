package ru.suleymanovtat.dressingbabyweather.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.suleymanovtat.dressingbabyweather.model.local.CityLocal
import ru.suleymanovtat.dressingbabyweather.model.local.SettingsLocal


@Entity(tableName = "settings_table")
data class Settings(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 1,
    @ColumnInfo(name = "city_id")
    var city_id: Int? = null,
    @ColumnInfo(name = "date")
    var date: Long = 0,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "lat")
    var lat: Double? = null,
    @ColumnInfo(name = "lng")
    var lng: Double? = null,
    @ColumnInfo(name = "isLogged")
    var isLogged: Boolean? = null
)

fun CityLocal.mapToSettings(): Settings {
    val city = this
    return Settings(city_id = city.id!!, name = city.name!!, lat = city.lat!!, lng = city.lng!!)
}

fun Settings.mapToLocal(): SettingsLocal {
    val model = this
    val settingsLocal = SettingsLocal()
    with(settingsLocal) {
        id = model.id
        cityId = model.city_id
        date = model.date
        name = model.name
        lat = model.lat
        lng = model.lng
        isLogged = model.isLogged
    }
    return settingsLocal
}



