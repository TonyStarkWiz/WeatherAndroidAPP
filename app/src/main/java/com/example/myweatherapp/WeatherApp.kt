package com.example.myweatherapp

import android.app.Application
import com.example.myweatherapp.di.NetworkModule
import com.example.myweatherapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApp)
            modules(listOf(NetworkModule, viewModelModule))
        }
    }
}