package ru.suleymanovtat.dressingbabyweather.repository

import com.crashlytics.android.Crashlytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.suleymanovtat.dressingbabyweather.model.database.Settings
import ru.suleymanovtat.dressingbabyweather.model.database.mapToLocal
import ru.suleymanovtat.dressingbabyweather.model.local.SettingsLocal
import ru.suleymanovtat.dressingbabyweather.model.local.mapToRoomModel
import ru.suleymanovtat.dressingbabyweather.model.network.Cities
import ru.suleymanovtat.dressingbabyweather.repository.database.AppDatabase


class SettingsRepository(val database: AppDatabase) {

    fun getSettingsFlow() = database.settingsDao().getSettingsFlow()

    fun getSettingsLocalFlow() =
        database.settingsDao().getSettingsFlow().map { it -> it?.mapToLocal() }

    fun saveDate(settingsLocal: SettingsLocal) =
        database.settingsDao().insertOrUpdate(settingsLocal.mapToRoomModel())

    fun setLogged(logged: Boolean) =
        database.settingsDao().insertOrUpdate(Settings(isLogged = logged))

    fun getCitiesFirebase() {
        val databaseFirebase = Firebase.database
        val myRef = databaseFirebase.getReference("weather_clothes").child("cities")
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Crashlytics.log(error.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                GlobalScope.launch {
                    try {
                        val cities = dataSnapshot.getValue(Cities::class.java)
                        val list = cities?.ru?.cities
                        list?.let {
                            database.cityDao().saveCities(it)
                        }
                    } catch (ex: Exception) {
                        Crashlytics.logException(ex)
                    }
                }
            }
        })
    }
}