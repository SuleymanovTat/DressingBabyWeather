package ru.suleymanovtat.dressingbabyweather

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import ru.suleymanovtat.dressingbabyweather.di.component.*
import ru.suleymanovtat.dressingbabyweather.di.module.*
import ru.suleymanovtat.dressingbabyweather.model.database.City
import ru.suleymanovtat.dressingbabyweather.model.database.Dress
import ru.suleymanovtat.dressingbabyweather.model.database.Settings
import ru.suleymanovtat.dressingbabyweather.model.database.WeatherDress
import ru.suleymanovtat.dressingbabyweather.repository.database.AppDatabase
import java.util.*


class App : Application() {

    private var viewModelComponent: ViewModelComponent? = null
    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        initRoom()
        initDagger()
//        saveDefaultData()
    }

    private fun initRoom() {
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database.db")
            .addCallback(CALLBACK)
            .build()
    }

    private fun initDagger() {
        val apiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule(this))
            .build()
        val databaseComponent = DaggerDatabaseComponent.builder()
            .databaseModule(DatabaseModule(this.database!!))
            .build()
        val repositoryComponent = DaggerRepositoryComponent.builder()
            .apiComponent(apiComponent)
            .databaseComponent(databaseComponent)
            .repositoryModule(RepositoryModule())
            .build()
        val iteractorComponent = DaggerIteractorComponent.builder()
            .repositoryComponent(repositoryComponent)
            .iteractorModule(IteractorModule())
            .build()
        viewModelComponent = DaggerViewModelComponent.builder()
            .viewModelModule(ViewModelModule(this))
            .iteractorComponent(iteractorComponent)
            .build()
    }

    fun getViewModelComponent(): ViewModelComponent {
        return this.viewModelComponent!!
    }

    private val CALLBACK = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            saveDefaultData()
        }
    }

    private fun saveDefaultData() {
        GlobalScope.launch {
            val json: String = assets.open("settings.json")
                .bufferedReader().use { it.readText() }
            val jsonObj = JSONObject(json)
            val gson = Gson()
            val settings: Settings = gson.fromJson(
                jsonObj.getJSONObject(Locale.getDefault().language).getJSONObject("settings")
                    .toString(),
                Settings::class.java
            ).apply { date = Calendar.getInstance().time.time }
            val citiesJson =
                jsonObj.getJSONObject(Locale.getDefault().language).getJSONArray("cities")
                    .toString()
            val cities: List<City> =
                gson.fromJson(citiesJson, object : TypeToken<List<City>>() {}.type)

            val jsonDress: String = assets.open("dress.json")
                .bufferedReader().use { it.readText() }
            val dressJsonObject = JSONObject(jsonDress)
            val dressJson =
                dressJsonObject.getJSONArray(Locale.getDefault().language).toString()
            val dress: List<Dress> =
                gson.fromJson(dressJson, object : TypeToken<List<Dress>>() {}.type)

            val jsonWeatherDress: String = assets.open("weather_dress.json")
                .bufferedReader().use { it.readText() }
            val weatherDressJsonObject = JSONObject(jsonWeatherDress)
            val weatherDressJson =
                weatherDressJsonObject.getJSONArray(Locale.getDefault().language).toString()
            val weatherDress: List<WeatherDress> =
                gson.fromJson(weatherDressJson, object : TypeToken<List<WeatherDress>>() {}.type)

            database?.weatherDressDao()?.saveWeatherDress(weatherDress)
            database?.dressDao()?.saveDress(dress)
            database?.cityDao()?.saveCities(cities)
            database?.settingsDao()?.insertOrUpdate(settings)
        }
    }
}