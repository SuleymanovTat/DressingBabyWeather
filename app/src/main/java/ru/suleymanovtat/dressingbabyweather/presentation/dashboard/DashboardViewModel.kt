package ru.suleymanovtat.dressingbabyweather.presentation.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.suleymanovtat.dressingbabyweather.di.module.BaseViewModel
import ru.suleymanovtat.dressingbabyweather.domain.HomeInteractor
import ru.suleymanovtat.dressingbabyweather.model.local.DressLocal
import ru.suleymanovtat.dressingbabyweather.utils.handleErrors

class DashboardViewModel(application: Application, val homeInteractor: HomeInteractor) :
    BaseViewModel(application) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            homeInteractor.loadingDress()
            homeInteractor.getDirectory().handleErrors().collect {
                if (it != null)
                    dashboards.postValue(it)
            }
        }
    }

    val dashboards = MutableLiveData<List<DressLocal>>()

    fun loadingDress() {
        homeInteractor.loadingDress()
    }
}