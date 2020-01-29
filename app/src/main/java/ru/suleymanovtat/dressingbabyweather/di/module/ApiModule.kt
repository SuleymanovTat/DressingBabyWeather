package ru.suleymanovtat.dressingbabyweather.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.suleymanovtat.dressingbabyweather.BuildConfig.BASE_URL
import ru.suleymanovtat.dressingbabyweather.di.scope.ApiScope
import ru.suleymanovtat.dressingbabyweather.repository.network.ApiService
import ru.suleymanovtat.dressingbabyweather.repository.network.ServerCommunicator
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


@Module
class ApiModule(var application: Application) {

    @Provides
    @ApiScope
    fun provideCommunicator(apiService: ApiService): ServerCommunicator {
        return ServerCommunicator(apiService)
    }

    @Provides
    @ApiScope
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(ApiService::class.java)
    }

    @Provides
    @ApiScope
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl(BASE_URL).build()
    }


    @Provides
    @ApiScope
    fun provideRetrofitBuilder(): Retrofit.Builder {
        val builder = OkHttpClient.Builder()
            .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        return Retrofit.Builder()
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

}
