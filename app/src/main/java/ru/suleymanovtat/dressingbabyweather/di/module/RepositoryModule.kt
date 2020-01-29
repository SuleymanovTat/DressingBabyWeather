package ru.suleymanovtat.dressingbabyweather.di.module

import dagger.Module
import dagger.Provides
import ru.suleymanovtat.dressingbabyweather.di.scope.RepositoryScope
import ru.suleymanovtat.dressingbabyweather.repository.CitiesRepository
import ru.suleymanovtat.dressingbabyweather.repository.HomeRepository
import ru.suleymanovtat.dressingbabyweather.repository.SettingsRepository
import ru.suleymanovtat.dressingbabyweather.repository.database.AppDatabase
import ru.suleymanovtat.dressingbabyweather.repository.network.ServerCommunicator

@Module
class RepositoryModule {

    @RepositoryScope
    @Provides
    internal fun providesRepository(
        communicator: ServerCommunicator,
        database: AppDatabase
    ) = HomeRepository(communicator, database)


    @RepositoryScope
    @Provides
    internal fun providesCitiesRepository(
        communicator: ServerCommunicator,
        database: AppDatabase
    ) = CitiesRepository(communicator, database)

    @RepositoryScope
    @Provides
    internal fun providesSettingsRepository(database: AppDatabase) = SettingsRepository(database)
}