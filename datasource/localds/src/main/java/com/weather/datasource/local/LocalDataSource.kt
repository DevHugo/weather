package com.weather.datasource.local

import android.content.Context
import androidx.room.Room
import com.weather.datasource.local.converter.WeatherDatabaseConverter
import com.weather.models.DailyWeather
import com.weather.models.HourlyWeather
import com.weather.repository.LocalDataSource

const val DATABASE_NAME = "weather"

class LocalDataSourceImpl (appContext: Context): LocalDataSource {
    private val db: AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME).build()

    override suspend fun fetchWeatherPerHourForDay(from: Long, to: Long): List<HourlyWeather> {
        val converter = WeatherDatabaseConverter()

        return db.weather()
            .getWeatherPerHour(from, to)
            .map { weatherEntity ->
                converter.convert(weatherEntity)
            }
    }

    override suspend fun fetchWeatherPerDay(from: Long, to: Long): List<DailyWeather> {
        val converter = WeatherDatabaseConverter()

        return db.weather()
            .getWeatherPerDay(from, to)
            .map { weatherEntity ->
                converter.convert(weatherEntity)
            }
    }

    override suspend fun addAllHourly(weathers: List<HourlyWeather>) {
        val converter = WeatherDatabaseConverter()
        db.weather().insertAllHourly(weathers.map { weather -> converter.convert(weather) })
    }

    override suspend fun addAllDaily(weathers: List<DailyWeather>) {
        val converter = WeatherDatabaseConverter()
        db.weather().insertAllDaily(weathers.map { weather -> converter.convert(weather) })
    }
}