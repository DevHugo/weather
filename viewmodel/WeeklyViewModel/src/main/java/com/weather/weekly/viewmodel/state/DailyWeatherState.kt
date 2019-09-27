package com.weather.weekly.viewmodel.state

import com.weather.models.Icon
import java.util.*

sealed class WeatherState {
    object Error : WeatherState()
    data class Perfect(val weeklyWeatherState: List<DailyWeatherState>): WeatherState()
}

data class DailyWeatherState(val date: Date, val icon: Icon?, val summary: String?) : WeatherState()