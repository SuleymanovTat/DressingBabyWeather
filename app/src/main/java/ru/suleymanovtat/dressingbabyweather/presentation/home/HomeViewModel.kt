package ru.suleymanovtat.dressingbabyweather.presentation.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.suleymanovtat.dressingbabyweather.di.module.BaseViewModel
import ru.suleymanovtat.dressingbabyweather.domain.HomeInteractor
import ru.suleymanovtat.dressingbabyweather.presentation.home.adapter.DiffItem
import ru.suleymanovtat.dressingbabyweather.utils.handleErrors

class HomeViewModel(application: Application, val homeInteractor: HomeInteractor) :
    BaseViewModel(application) {

    val dashboards = MutableLiveData<List<DiffItem>>()
    var list = arrayListOf<DiffItem>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            homeInteractor.getList()?.handleErrors()?.collect {
                dashboards.postValue(it)
                list = it
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun loading() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = homeInteractor.getWeather()
            if (result.status == 0) {
                dashboards.postValue(list)
            }
        }
    }
}