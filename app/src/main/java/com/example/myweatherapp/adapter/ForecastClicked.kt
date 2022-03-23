package com.example.myweatherapp.adapter

import com.example.myweatherapp.model.Forecast

interface ForecastClicked {
    fun onForecastClick(forecast: Forecast)
}