package ru.suleymanovtat.dressingbabyweather.di.module

import dagger.Module
import dagger.Provides
import ru.suleymanovtat.dressingbabyweather.repository.database.AppDatabase

@Module
class DatabaseModule(private val appDatabase: AppDatabase) {
    @Provides
    internal fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }
}