package ru.suleymanovtat.dressingbabyweather.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.suleymanovtat.dressingbabyweather.App
import ru.suleymanovtat.dressingbabyweather.di.scope.ViewModelScope
import ru.suleymanovtat.dressingbabyweather.domain.CitiesInteractor
import ru.suleymanovtat.dressingbabyweather.domain.HomeInteractor
import ru.suleymanovtat.dressingbabyweather.domain.SettingsInteractor
import ru.suleymanovtat.dressingbabyweather.presentation.city.ListCityViewModel
import ru.suleymanovtat.dressingbabyweather.presentation.dashboard.DashboardViewModel
import ru.suleymanovtat.dressingbabyweather.presentation.home.HomeViewModel
import ru.suleymanovtat.dressingbabyweather.presentation.main.MainViewModel
import ru.suleymanovtat.dressingbabyweather.presentation.onboarding.OnboardingViewModel
import ru.suleymanovtat.dressingbabyweather.presentation.onboarding.fragment.CitiesViewModel
import ru.suleymanovtat.dressingbabyweather.presentation.onboarding.fragment.DateOfBirthViewModel
import ru.suleymanovtat.dressingbabyweather.presentation.settings.SettingsViewModel

@Module
class ViewModelModule(app: App) {

    internal var app: Application = app

    @ViewModelScope
    @Provides
    internal fun providesDashboardViewModel(homeInteractor: HomeInteractor) =
        DashboardViewModel(app, homeInteractor)


    @ViewModelScope
    @Provides
    internal fun providesHomeViewModel(homeInteractor: HomeInteractor) =
        HomeViewModel(app, homeInteractor)


    @ViewModelScope
    @Provides
    internal fun providesListCityViewModel(citiesInteractor: CitiesInteractor) =
        ListCityViewModel(app, citiesInteractor)

    @ViewModelScope
    @Provides
    internal fun providesCityViewModel(citiesInteractor: CitiesInteractor) =
        CitiesViewModel(app, citiesInteractor)


    @ViewModelScope
    @Provides
    internal fun providesDateOfBirthFragment(settingsInteractor: SettingsInteractor) =
        DateOfBirthViewModel(app, settingsInteractor)

    @ViewModelScope
    @Provides
    internal fun providesSettingsFragment(settingsInteractor: SettingsInteractor) =
        SettingsViewModel(app, settingsInteractor)


    @ViewModelScope
    @Provides
    internal fun providesOnboardingActivity(settingsInteractor: SettingsInteractor) =
        OnboardingViewModel(app, settingsInteractor)

    @ViewModelScope
    @Provides
    internal fun providesMainActivity(homeInteractor: HomeInteractor) =
        MainViewModel(app, homeInteractor)
}