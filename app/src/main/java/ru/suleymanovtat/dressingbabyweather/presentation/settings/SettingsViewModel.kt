package ru.suleymanovtat.dressingbabyweather.presentation.settings

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.suleymanovtat.dressingbabyweather.di.module.BaseViewModel
import ru.suleymanovtat.dressingbabyweather.domain.SettingsInteractor
import ru.suleymanovtat.dressingbabyweather.model.local.SettingsLocal


class SettingsViewModel(application: Application,
    val interactor: SettingsInteractor
) : BaseViewModel(application) {

    val settingsLiveData = MutableLiveData<SettingsLocal>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getSettingsLocalFlow().collect {
                settingsLiveData.postValue(it)
            }
        }
    }

    fun clearStorage() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.setLogged(false)
        }
    }

    fun saveDate(date: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            settingsLiveData.value?.date = date
            val settingsLocal: SettingsLocal = settingsLiveData.value as SettingsLocal
            interactor.saveDate(settingsLocal)
        }
    }

    fun getDate(): Long {
        return settingsLiveData.value?.date!!
    }
}




