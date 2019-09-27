package com.weather.repository

import com.weather.models.DailyWeather
import com.weather.models.HourlyWeather
import com.weather.models.Icon
import com.weather.models.UserPref

val userPref = UserPref(0F, 1F, "fr", "auto")
val hourlyWeather = HourlyWeather(1L, Icon.CLEAR_DAY, "Ciel nuageux", 99F, 30F, 25F, 10F, 0.10F, 0.10F)

val dailyWeather = DailyWeather(1L, Icon.CLEAR_DAY, "Ciel nuageux")

interface MockRemoteDataSource : RemoteDataSource {
    override suspend fun fetchWeatherPerHourForDay(timestamp: Long, userPref: UserPref) = emptyList<HourlyWeather>()
    override suspend fun fetchWeatherPerDayForNextWeek(userPref: UserPref) = emptyList<DailyWeather>()
}

interface MockLocalDataSource : LocalDataSource {
    override suspend fun fetchWeatherPerHourForDay(from: Long, to: Long) = emptyList<HourlyWeather>()
    override suspend fun addAllHourly(weathers: List<HourlyWeather>) {}

    override suspend fun fetchWeatherPerDay(from: Long, to: Long) = emptyList<DailyWeather>()
    override suspend fun addAllDaily(weathers: List<DailyWeather>) {}
}