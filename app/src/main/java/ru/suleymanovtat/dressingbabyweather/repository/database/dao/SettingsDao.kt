package ru.suleymanovtat.dressingbabyweather.repository.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.suleymanovtat.dressingbabyweather.model.database.Settings

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCities(settings: List<Settings>)

    @Query("SELECT * from settings_table LIMIT 1")
    fun getSettings(): Settings

    @Query("SELECT * from settings_table LIMIT 1")
    fun getSettingsFlow(): Flow<Settings?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(settings: Settings): Long

    @Update
    fun update(settings: Settings)

    @Query("DELETE FROM settings_table")
    fun delete()

    @Transaction
    fun insertOrUpdate(settings: Settings) {
        if (insertIgnore(settings) == -1L) {
            val settingsOld = getSettings()
            if (settings.city_id == null) {
                settings.city_id = settingsOld.city_id ?: 3
            }
            if (settings.date == 0L) {
                settings.date = settingsOld.date
            }
            if (settings.name == null) {
                settings.name = settingsOld.name
            }
            if (settings.lat == null) {
                settings.lat = settingsOld.lat
            }
            if (settings.lng == null) {
                settings.lng = settingsOld.lng
            }
            if (settings.isLogged == null) {
                settings.isLogged = settingsOld.isLogged
            }
            update(settings)
        }
    }
}