package ru.suleymanovtat.dressingbabyweather.model.network

import ru.suleymanovtat.dressingbabyweather.model.network.Main
import ru.suleymanovtat.dressingbabyweather.model.network.Weather
import ru.suleymanovtat.dressingbabyweather.presentation.home.adapter.DiffItem

data class WeatherBase(

    val weather: List<Weather>? = null,
    val main: Main? = null,
    val dt: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val cod: Int? = null
) : DiffItem {
    override fun isHeader()=true

    override fun getItemId(): Long {
        return id?.toLong() ?: 0L
    }

    override fun getItemHash(): Int {
        return hashCode()
    }
}