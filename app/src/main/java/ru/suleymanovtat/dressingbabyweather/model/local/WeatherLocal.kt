package ru.suleymanovtat.dressingbabyweather.model.local

import ru.suleymanovtat.dressingbabyweather.presentation.home.adapter.DiffItem

data class WeatherLocal(

    val id: Int,
    val name: String,
    val description: String,
    val temp: String,
    val image: String
) : DiffItem {

    override fun isHeader() = true

    override fun getItemId(): Long {
        return id?.toLong() ?: 0L
    }

    override fun getItemHash(): Int {
        return hashCode()
    }
}