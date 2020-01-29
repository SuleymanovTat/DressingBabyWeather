package ru.suleymanovtat.dressingbabyweather.di.module

import dagger.Module
import dagger.Provides
import ru.suleymanovtat.dressingbabyweather.di.scope.InteractorScope
import ru.suleymanovtat.dressingbabyweather.domain.CitiesInteractor
import ru.suleymanovtat.dressingbabyweather.domain.HomeInteractor
import ru.suleymanovtat.dressingbabyweather.domain.SettingsInteractor
import ru.suleymanovtat.dressingbabyweather.repository.CitiesRepository
import ru.suleymanovtat.dressingbabyweather.repository.HomeRepository
import ru.suleymanovtat.dressingbabyweather.repository.SettingsRepository

@Module
class IteractorModule {

    @InteractorScope
    @Provides
    internal fun providesSettingsFragment(repository: SettingsRepository) =
        SettingsInteractor(repository)

    @InteractorScope
    @Provides
    internal fun providesListCityFragment(repository: CitiesRepository) =
        CitiesInteractor(repository)

    @InteractorScope
    @Provides
    internal fun providesDashboardFragment(repository: HomeRepository) =
        HomeInteractor(repository)
}