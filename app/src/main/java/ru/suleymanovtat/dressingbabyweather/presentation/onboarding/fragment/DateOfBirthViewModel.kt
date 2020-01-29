package ru.suleymanovtat.dressingbabyweather.presentation.onboarding.fragment

import android.app.Application
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.suleymanovtat.dressingbabyweather.di.module.BaseViewModel
import ru.suleymanovtat.dressingbabyweather.domain.SettingsInteractor
import ru.suleymanovtat.dressingbabyweather.model.local.SettingsLocal
import java.util.*

class DateOfBirthViewModel(
    application: Application,
    val settingsInteractor: SettingsInteractor
) : BaseViewModel(application) {

    fun saveDate(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val cal = Calendar.getInstance()
        cal.timeInMillis = 0
        cal.set(year, monthOfYear, dayOfMonth, 0, 0, 0)
        val time = cal.time.time
        viewModelScope.launch(Dispatchers.IO) {
            settingsInteractor.saveDate(
                SettingsLocal(
                    date = time
                )
            )
        }
    }
}
