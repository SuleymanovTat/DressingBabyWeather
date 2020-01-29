package ru.suleymanovtat.dressingbabyweather.model.local

import ru.suleymanovtat.dressingbabyweather.model.database.City

class CityLocal(
    var id: Int? = null,
    var name: String? = null,
    var lat: Double? = null,
    var lng: Double? = null,
    var selected: Boolean = false
)

fun City.mapToLocal(): CityLocal {
    val model = this
    val cityLocal = CityLocal()
    with(cityLocal) {
        id = model.id
        name = model.name
        lat = model.lat
        lng = model.lng
    }
    return cityLocal
}