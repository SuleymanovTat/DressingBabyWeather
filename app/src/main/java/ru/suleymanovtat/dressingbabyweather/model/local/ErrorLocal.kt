package ru.suleymanovtat.dressingbabyweather.model.local

import ru.suleymanovtat.dressingbabyweather.presentation.home.adapter.DiffItem

class ErrorLocal(
    val id: String? = null,
    val desc: String? = null,
    val image: String? = null
) : DiffItem {
    override fun isHeader() = false

    override fun getItemId(): Long {
        return id?.toLong() ?: 0
    }

    override fun getItemHash(): Int {
        return hashCode()
    }
}