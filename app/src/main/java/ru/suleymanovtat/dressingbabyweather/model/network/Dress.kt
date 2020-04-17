package ru.suleymanovtat.dressingbabyweather.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.suleymanovtat.dressingbabyweather.model.database.Dress

class Dresses {
    @SerializedName("ru")
    @Expose
    var ru: List<Dress>? = null
    @SerializedName("en")
    @Expose
    var en: List<Dress>? = null
}

