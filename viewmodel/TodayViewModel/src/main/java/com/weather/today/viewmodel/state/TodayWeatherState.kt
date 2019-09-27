package com.weather.today.viewmodel.state

import com.weather.models.Icon
import java.util.*

sealed class WeatherState {
    object Error : WeatherState()
    data class Perfect(val state: TodayWeatherState): WeatherState()
}

data class TodayWeatherState(
    val date: Date,
    val icon: Icon?,
    val summary: String?,
    val precipProbability: String?,
    val temperature: String?,
    val minTemperature: String?,
    val maxTemperature: String?,
    val windSpeed: String?,
    val cloudCover: String?) : WeatherState()