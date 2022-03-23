package com.example.myweatherapp.rest

import com.example.myweatherapp.model.Results
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(ENDPOINT)
    suspend fun getForecast(
        @Query("q") city : String,
        @Query("appId") apiKey : String = API_KEY,
    ) : Response<Results>

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val ENDPOINT = "forecast"

        private const val API_KEY = "65d00499677e59496ca2f318eb68c049"
    }
}