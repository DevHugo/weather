package com.weather.repository

import com.weather.models.DailyWeather
import com.weather.models.HourlyWeather
import com.weather.models.UserPref

interface RemoteDataSource {
    suspend fun fetchWeatherPerHourForDay(timestamp: Long, userPref: UserPref): List<HourlyWeather>
    suspend fun fetchWeatherPerDayForNextWeek(userPref: UserPref): List<DailyWeather>
}

interface LocalDataSource {
    suspend fun fetchWeatherPerHourForDay(from: Long, to: Long): List<HourlyWeather>
    suspend fun addAllHourly(weathers: List<HourlyWeather>)

    suspend fun addAllDaily(weathers: List<DailyWeather>)
    suspend fun fetchWeatherPerDay(from: Long, to: Long): List<DailyWeather>
}