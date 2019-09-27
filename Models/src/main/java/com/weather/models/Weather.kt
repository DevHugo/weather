package com.weather.models

enum class Icon { CLEAR_DAY, CLEAR_NIGHT, RAIN, SNOW, SLEET, WIND, FOG, CLOUDY, PARTLY_CLOUDY_DAY, PARTLY_CLOUDY_NIGHT, OTHER }

data class HourlyWeather (
    val timestamp: Long,
    val icon: Icon,
    val summary: String?,
    val precipProbability: Float?,
    val temperature: Float?,
    val apparentTemperature: Float?,
    val humidity: Float?,
    val windSpeed: Float?,
    val cloudCover: Float?)

data class DailyWeather (
    val timestamp: Long,
    val icon: Icon,
    val summary: String?)