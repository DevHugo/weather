package com.weather.datasource.local.converter

import com.weather.datasource.local.entity.DailyWeatherEntity
import com.weather.datasource.local.entity.HourlyWeatherEntity
import com.weather.models.DailyWeather
import com.weather.models.HourlyWeather

class WeatherDatabaseConverter {
    fun convert (from: HourlyWeatherEntity) =
        HourlyWeather(from.timestamp, IconConverter.convert(from.icon), from.summary, from.precipProbability, from.temperature,
            from.apparentTemperature, from.humidity, from.windSpeed, from.cloudCover)

    fun convert (from: DailyWeatherEntity) =
        DailyWeather(from.timestamp, IconConverter.convert(from.icon), from.summary)

    fun convert (from: HourlyWeather) =
        HourlyWeatherEntity(from.timestamp, IconConverter.convert(from.icon), from.summary, from.precipProbability, from.temperature,
            from.apparentTemperature, from.humidity, from.windSpeed, from.cloudCover)

    fun convert (from: DailyWeather) =
        DailyWeatherEntity(from.timestamp, IconConverter.convert(from.icon), from.summary)
}