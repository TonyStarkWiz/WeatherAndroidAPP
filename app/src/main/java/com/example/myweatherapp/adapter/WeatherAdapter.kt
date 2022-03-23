package com.example.myweatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.databinding.ForecastItemBinding
import com.example.myweatherapp.kelvinToFahrenheit
import com.example.myweatherapp.model.Forecast
import com.example.myweatherapp.roundToFloor
import java.math.RoundingMode
import java.text.DecimalFormat

class WeatherAdapter(
    private val forecastList: MutableList<Forecast> = mutableListOf(),
    private val onForecastClicked: (Forecast) -> Unit
) : RecyclerView.Adapter<WeatherViewHolder>(){

    fun setForecast(newForecast: List<Forecast>) {
        forecastList.clear()
        forecastList.addAll(newForecast)
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = ForecastItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherViewHolder(view, onForecastClicked)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) =
        holder.bind(forecastList[position])

    override fun getItemCount(): Int = forecastList.size
}

class WeatherViewHolder(
    private val binding: ForecastItemBinding,
    private val onForecastClicked: (Forecast) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: Forecast) {
        val temp = kelvinToFahrenheit(forecast.main.temp)
        val finalTemp = roundToFloor(temp)
        val feelsLike = kelvinToFahrenheit(forecast.main.feelsLike)
        val finalFeelsLike = roundToFloor(feelsLike)
        binding.temperature.text = "$finalTemp\u2109"
        binding.dateTime.text = forecast.dtTxt
        binding.feelsLike.text = "$finalFeelsLike\u2109"

        binding.forecastItem.setOnClickListener {
            onForecastClicked.invoke(forecast)
        }

//        binding.description.text = forecast.weather.firstOrNull()?.description
    }
}