package ru.suleymanovtat.dressingbabyweather.model.network

data class Main(

    val temp: String? = null,
    val pressure: String? = null,
    val humidity: Int,
    val tempMin: Double,
    val tempMax: Double
)