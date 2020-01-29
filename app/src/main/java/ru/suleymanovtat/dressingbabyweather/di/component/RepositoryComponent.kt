package ru.suleymanovtat.dressingbabyweather.di.component

import dagger.Component
import ru.suleymanovtat.dressingbabyweather.di.module.RepositoryModule
import ru.suleymanovtat.dressingbabyweather.di.scope.RepositoryScope
import ru.suleymanovtat.dressingbabyweather.repository.CitiesRepository
import ru.suleymanovtat.dressingbabyweather.repository.HomeRepository
import ru.suleymanovtat.dressingbabyweather.repository.SettingsRepository


@RepositoryScope
@Component(
    modules = [RepositoryModule::class],
    dependencies = [ApiComponent::class, DatabaseComponent::class]
)
interface RepositoryComponent {
    val repository: HomeRepository
    val cityRepository: CitiesRepository
    val settingsRepository: SettingsRepository
}