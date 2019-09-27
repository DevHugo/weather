package com.weather.datasource.remote.converter

import com.weather.datasource.remote.dto.DailyWeatherDto
import com.weather.datasource.remote.dto.HourlyWeatherDto
import com.weather.models.DailyWeather
import com.weather.models.HourlyWeather

object WeatherDataConverter {

    fun convert (from: HourlyWeatherDto) =
        HourlyWeather(from.timestamp, IconConverter.convert(from.icon), from.summary, from.precipProbability, from.temperature,
            from.apparentTemperature, from.humidity, from.windSpeed, from.cloudCover)

    fun convert (from: DailyWeatherDto) =
        DailyWeather(from.timestamp, IconConverter.convert(from.icon), from.summary)
}