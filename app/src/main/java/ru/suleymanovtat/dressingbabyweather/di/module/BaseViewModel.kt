package ru.suleymanovtat.dressingbabyweather.di.module

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import ru.suleymanovtat.dressingbabyweather.App

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    fun getContext() = getApplication<App>()
    fun getString(@StringRes id: Int): String = getContext().getString(id)
}