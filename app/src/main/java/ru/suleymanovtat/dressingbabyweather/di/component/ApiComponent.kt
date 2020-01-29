package ru.suleymanovtat.dressingbabyweather.di.component

import dagger.Component
import ru.suleymanovtat.dressingbabyweather.di.module.ApiModule
import ru.suleymanovtat.dressingbabyweather.di.scope.ApiScope
import ru.suleymanovtat.dressingbabyweather.repository.network.ServerCommunicator


@ApiScope
@Component(modules = [ApiModule::class])
interface ApiComponent {
    val serverCommunicator: ServerCommunicator
}
