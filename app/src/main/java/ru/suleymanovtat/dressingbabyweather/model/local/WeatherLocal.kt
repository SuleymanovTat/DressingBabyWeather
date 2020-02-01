package ru.suleymanovtat.dressingbabyweather.model.local

import ru.suleymanovtat.dressingbabyweather.presentation.home.adapter.DiffItem

data class WeatherLocal(

    val id: Int,
    val name: String,
    val description: String,
    val temp: String,
    val image: String,
    override val itemId: Long = id?.toLong() ?: 0L,
    override val isHeader: Boolean = true
) : DiffItem