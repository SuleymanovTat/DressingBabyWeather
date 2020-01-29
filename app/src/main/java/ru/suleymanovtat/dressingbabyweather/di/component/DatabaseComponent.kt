package ru.suleymanovtat.dressingbabyweather.di.component


import dagger.Component
import ru.suleymanovtat.dressingbabyweather.di.module.DatabaseModule
import ru.suleymanovtat.dressingbabyweather.repository.database.AppDatabase


@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppDatabase
}
