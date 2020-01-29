package ru.suleymanovtat.dressingbabyweather.presentation.onboarding

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.suleymanovtat.dressingbabyweather.di.module.BaseViewModel
import ru.suleymanovtat.dressingbabyweather.domain.SettingsInteractor
import ru.suleymanovtat.dressingbabyweather.repository.SettingsRepository
import ru.suleymanovtat.dressingbabyweather.utils.handleErrors

class OnboardingViewModel(
    application: Application,
    private val settingsInteractor: SettingsInteractor
) : BaseViewModel(application) {

    val isLogged = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            settingsInteractor.getSettingsFlow().handleErrors().collect { it ->
                isLogged.postValue(it?.isLogged ?: false)
            }
        }
    }

    fun isLogged(logged: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            settingsInteractor.setLogged(logged)
        }
    }
}
