package ru.suleymanovtat.dressingbabyweather.di.component

import dagger.Component
import ru.suleymanovtat.dressingbabyweather.di.module.ViewModelModule
import ru.suleymanovtat.dressingbabyweather.di.scope.ViewModelScope
import ru.suleymanovtat.dressingbabyweather.presentation.city.ListCityFragment
import ru.suleymanovtat.dressingbabyweather.presentation.dashboard.DashboardFragment
import ru.suleymanovtat.dressingbabyweather.presentation.home.HomeFragment
import ru.suleymanovtat.dressingbabyweather.presentation.main.MainActivity
import ru.suleymanovtat.dressingbabyweather.presentation.onboarding.OnboardingActivity
import ru.suleymanovtat.dressingbabyweather.presentation.onboarding.fragment.CitiesFragment
import ru.suleymanovtat.dressingbabyweather.presentation.onboarding.fragment.DateOfBirthFragment
import ru.suleymanovtat.dressingbabyweather.presentation.settings.SettingsFragment

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [ IteractorComponent::class])
interface ViewModelComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: OnboardingActivity)

    fun inject(fragment: DashboardFragment)
    fun inject(fragment: ListCityFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: CitiesFragment)
    fun inject(fragment: DateOfBirthFragment)
    fun inject(fragment: SettingsFragment)
}