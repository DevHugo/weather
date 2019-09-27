package com.weather.repository

import com.weather.models.DailyWeather
import com.weather.models.HourlyWeather
import com.weather.models.RemoteException
import com.weather.models.UserPref

interface WeatherRepository {
    suspend fun fetchWeatherForDay(from: Long, userPref: UserPref): List<HourlyWeather>
    suspend fun fetchWeatherForWeek(userPref: UserPref): List<DailyWeather>
}

class WeatherRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource) : WeatherRepository {

    override suspend fun fetchWeatherForDay(from: Long, userPref: UserPref): List<HourlyWeather> {
        try {
            val weatherData = remoteDataSource.fetchWeatherPerHourForDay(from, userPref)
            localDataSource.addAllHourly(weatherData)
        } catch (e: RemoteException) {}

        val nextDay = from + 60 * 60 * 24
        return localDataSource.fetchWeatherPerHourForDay(from, nextDay)
    }

    override suspend fun fetchWeatherForWeek(userPref: UserPref): List<DailyWeather> {
        try {
            val weatherData = remoteDataSource.fetchWeatherPerDayForNextWeek(userPref)
            localDataSource.addAllDaily(weatherData)
        } catch (e: RemoteException) { }

        val now = System.currentTimeMillis() / 1000
        val to = (7 * 24 * 60 * 60) + now
        return localDataSource.fetchWeatherPerDay(now, to)
    }
}