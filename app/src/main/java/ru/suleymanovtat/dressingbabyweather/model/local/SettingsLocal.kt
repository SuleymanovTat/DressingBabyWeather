package ru.suleymanovtat.dressingbabyweather.model.local

import ru.suleymanovtat.dressingbabyweather.model.database.Settings

class SettingsLocal(
    var id: Int = 1,
    var cityId: Int? = null,
    var date: Long = 0,
    var name: String? = null,
    var lat: Double? = null,
    var lng: Double? = null,
    var isLogged: Boolean? = null
)

fun SettingsLocal.mapToRoomModel(): Settings {
    val model = this
    val settings = Settings()
    with(settings) {
        id = model.id
        city_id = model.cityId
        date = model.date
        name = model.name
        lat = model.lat
        lng = model.lng
        isLogged = model.isLogged
    }
    return settings
}