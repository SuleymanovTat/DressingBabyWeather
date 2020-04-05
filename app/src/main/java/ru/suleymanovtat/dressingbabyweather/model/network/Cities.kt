package ru.suleymanovtat.dressingbabyweather.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.suleymanovtat.dressingbabyweather.model.database.City
import java.util.*

class Cities {
    @SerializedName("ru")
    @Expose
    var ru: Local? = null
    @SerializedName("en")
    @Expose
    var en: Local? = null
}

 class Local {
    @SerializedName("settings")
    @Expose
    var settings: Settings? = null
    @SerializedName("cities")
    @Expose
    var cities: List<City>? = null
}

 class Settings {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("city_id")
    @Expose
    var cityId: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("lat")
    @Expose
    var lat: Double? = null
    @SerializedName("lng")
    @Expose
    var lng: Double? = null
}