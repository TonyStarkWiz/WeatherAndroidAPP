package com.example.myweatherapp

import java.math.RoundingMode
import java.text.DecimalFormat

fun kelvinToFahrenheit(kelvin: Double): Double {
    return 1.8 * (kelvin - 273) + 32
}

fun roundToFloor(double: Double): String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.FLOOR
    return df.format(double)
}