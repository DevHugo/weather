package com.weather.weekly.viewmodel

import com.weather.models.DailyWeather
import com.weather.models.HourlyWeather
import com.weather.models.Icon
import com.weather.models.UserPref
import com.weather.repository.WeatherRepository

val dailyWeather = DailyWeather(System.currentTimeMillis(), Icon.CLEAR_DAY, "Ciel nuageux")

val userPref = UserPref(1F, 1F, "fr", "auto")

interface MockWeatherRepository : WeatherRepository {
    override suspend fun fetchWeatherForDay(from: Long, userPref: UserPref) = emptyList<HourlyWeather>()
    override suspend fun fetchWeatherForWeek(userPref: UserPref) = emptyList<DailyWeather>()
}