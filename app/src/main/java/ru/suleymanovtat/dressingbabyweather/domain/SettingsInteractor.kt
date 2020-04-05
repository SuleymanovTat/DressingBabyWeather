package ru.suleymanovtat.dressingbabyweather.domain

import ru.suleymanovtat.dressingbabyweather.model.local.SettingsLocal
import ru.suleymanovtat.dressingbabyweather.repository.SettingsRepository

class SettingsInteractor(val repository: SettingsRepository) {

    fun getSettingsFlow() = repository.getSettingsFlow()

    fun getSettingsLocalFlow() = repository.getSettingsLocalFlow()

    fun saveDate(settingsLocal: SettingsLocal) = repository.saveDate(settingsLocal)

    fun setLogged(logged: Boolean) = repository.setLogged(logged)

    fun getCitiesFirebase() = repository.getCitiesFirebase()
}