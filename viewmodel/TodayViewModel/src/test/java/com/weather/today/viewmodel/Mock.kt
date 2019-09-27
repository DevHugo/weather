package com.weather.today.viewmodel

import com.weather.models.DailyWeather
import com.weather.models.HourlyWeather
import com.weather.models.Icon
import com.weather.models.UserPref
import com.weather.repository.WeatherRepository

val hourlyWeather = HourlyWeather(System.currentTimeMillis(), Icon.CLEAR_DAY, "Ciel nuageux",
    0.99F, 30F, 25F, 10F,
    10F, 0.10F)

val userPref = UserPref(1F, 1F, "fr", "auto")

interface MockWeatherRepository : WeatherRepository {
    override suspend fun fetchWeatherForDay(from: Long, userPref: UserPref) = emptyList<HourlyWeather>()
    override suspend fun fetchWeatherForWeek(userPref: UserPref) = emptyList<DailyWeather>()
}