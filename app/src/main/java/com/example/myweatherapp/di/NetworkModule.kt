package com.example.myweatherapp.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.rest.WeatherApi
import com.example.myweatherapp.rest.WeatherRepository
import com.example.myweatherapp.rest.WeatherRepositoryImpl
import com.example.myweatherapp.viewmodel.WeatherViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {

    fun providesMoshi() = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    fun providesServiceApi(okHttpClient: OkHttpClient, moshi: Moshi) =
        Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(WeatherApi::class.java)

    single { providesMoshi() }
    single { providesLoggingInterceptor() }
    single { providesOkHttpClient(get()) }
    single { providesServiceApi(get(), get()) }
}

val viewModelModule = module {

    fun providesWeatherRepository(weatherApi: WeatherApi) : WeatherRepository =
        WeatherRepositoryImpl(weatherApi)

    single { providesWeatherRepository(get()) }
    viewModel { WeatherViewModel(get()) }
}
