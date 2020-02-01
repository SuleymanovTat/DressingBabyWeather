package ru.suleymanovtat.dressingbabyweather.model.network

import ru.suleymanovtat.dressingbabyweather.presentation.home.adapter.DiffItem

data class WeatherBase(

    val weather: List<Weather>? = null,
    val main: Main? = null,
    val dt: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val cod: Int? = null,
    override val itemId: Long = id?.toLong() ?: 0L,
    override val isHeader: Boolean = true
) : DiffItem
//{
//    override fun isHeader()=true
//
//    override fun getItemId(): Long {
//        return id?.toLong() ?: 0L
//    }
//
//    override fun getItemHash(): Int {
//        return hashCode()
//    }
//}