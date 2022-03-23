package com.example.myweatherapp.rest

import com.example.myweatherapp.model.Results
import retrofit2.Response

class WeatherRepositoryImpl (
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getCityForecast(city: String): Response<Results> {
        return weatherApi.getForecast(city)
    }

}

interface WeatherRepository {
    suspend fun getCityForecast(city: String) : Response<Results>
}