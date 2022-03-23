package com.example.myweatherapp.viewmodel

import com.example.myweatherapp.model.Results

sealed class ResultState {
    object LOADING : ResultState()
    class SUCCESS(val results: Results) : ResultState()
    class ERROR(val error: Throwable) : ResultState()
}