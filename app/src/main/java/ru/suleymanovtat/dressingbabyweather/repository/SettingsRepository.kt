package ru.suleymanovtat.dressingbabyweather.repository

import kotlinx.coroutines.flow.map
import ru.suleymanovtat.dressingbabyweather.model.local.SettingsLocal
import ru.suleymanovtat.dressingbabyweather.model.local.mapToRoomModel
import ru.suleymanovtat.dressingbabyweather.repository.database.AppDatabase
import ru.suleymanovtat.dressingbabyweather.model.database.Settings
import ru.suleymanovtat.dressingbabyweather.model.database.mapToLocal


class SettingsRepository(val database: AppDatabase) {

    fun getSettingsFlow() = database.settingsDao().getSettingsFlow()

    fun getSettingsLocalFlow() = database.settingsDao().getSettingsFlow().map { it -> it?.mapToLocal() }

    fun saveDate(settingsLocal: SettingsLocal) =
        database.settingsDao().insertOrUpdate(settingsLocal.mapToRoomModel())

    fun setLogged(logged: Boolean) = database.settingsDao().insertOrUpdate(Settings(isLogged = logged))
}