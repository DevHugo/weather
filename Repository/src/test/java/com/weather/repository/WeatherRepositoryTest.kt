package com.weather.repository

import com.weather.models.DailyWeather
import com.weather.models.HourlyWeather
import com.weather.models.Icon
import com.weather.models.UserPref
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WeatherRepositoryTest {

    @Test
    fun fetchWeatherForHourly() {

        val remoteDataSource = object : MockRemoteDataSource {
            override suspend fun fetchWeatherPerHourForDay(timestamp: Long, userPref: UserPref) =
                listOf(hourlyWeather)
        }
        val localDataSource = object : MockLocalDataSource {
            private var database: List<HourlyWeather> = listOf()
            override suspend fun addAllHourly(weathers: List<HourlyWeather>) {
                database = weathers
            }
            override suspend fun fetchWeatherPerHourForDay(from: Long, to: Long) = database
        }

        runBlocking {
            val result = WeatherRepositoryImpl(remoteDataSource, localDataSource)
                .fetchWeatherForDay(0L, userPref)
            assertEquals(hourlyWeather, result[0])
        }
    }

    @Test
    fun fetchWeatherForWeek() {
        val remoteDataSource = object : MockRemoteDataSource {
            override suspend fun fetchWeatherPerDayForNextWeek(userPref: UserPref) = listOf(dailyWeather)
        }
        val localDataSource = object : MockLocalDataSource {
            private var database: List<DailyWeather> = listOf()
            override suspend fun addAllDaily(weathers: List<DailyWeather>) {
                database = weathers
            }
            override suspend fun fetchWeatherPerDay(from: Long, to: Long) = database
        }

        runBlocking {
            val result = WeatherRepositoryImpl(remoteDataSource, localDataSource)
                .fetchWeatherForWeek(userPref)
            assertEquals(dailyWeather, result[0])
        }
    }
}