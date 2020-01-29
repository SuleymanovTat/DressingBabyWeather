package ru.suleymanovtat.dressingbabyweather.di.component

import dagger.Component
import ru.suleymanovtat.dressingbabyweather.di.module.IteractorModule
import ru.suleymanovtat.dressingbabyweather.di.scope.InteractorScope
import ru.suleymanovtat.dressingbabyweather.domain.CitiesInteractor
import ru.suleymanovtat.dressingbabyweather.domain.HomeInteractor
import ru.suleymanovtat.dressingbabyweather.domain.SettingsInteractor


@InteractorScope
@Component(
    modules = [IteractorModule::class],
    dependencies = [RepositoryComponent::class]
)
interface IteractorComponent {
    val citiesInteractor: CitiesInteractor
    val settingsInteractor: SettingsInteractor
    val homeInteractor: HomeInteractor
}