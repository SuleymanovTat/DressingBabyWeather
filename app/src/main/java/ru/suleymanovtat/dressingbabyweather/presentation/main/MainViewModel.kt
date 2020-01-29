package ru.suleymanovtat.dressingbabyweather.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.suleymanovtat.dressingbabyweather.di.module.BaseViewModel
import ru.suleymanovtat.dressingbabyweather.domain.HomeInteractor

class MainViewModel(application: Application, val homeInteractor: HomeInteractor) :
    BaseViewModel(application) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = homeInteractor.getWeather()
            if (result.status == 0) {
                Log.e("my", "Error " + result.error?.message)
            }
        }
    }
}

