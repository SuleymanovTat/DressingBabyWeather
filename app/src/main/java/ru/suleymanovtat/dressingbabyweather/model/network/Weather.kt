package ru.suleymanovtat.dressingbabyweather.model.network

data class Weather (

	val id : Int,
	val main : String,
	val description : String,
	val icon : String
)