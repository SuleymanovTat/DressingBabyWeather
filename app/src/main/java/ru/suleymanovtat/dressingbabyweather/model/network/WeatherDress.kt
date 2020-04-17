package ru.suleymanovtat.dressingbabyweather.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.suleymanovtat.dressingbabyweather.model.database.WeatherDress

class WeatherDress {
    @SerializedName("ru")
    @Expose
    var ru: List<WeatherDress>? = null

    @SerializedName("en")
    @Expose
    var en: List<WeatherDress>? = null
}