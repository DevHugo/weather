package com.weather.datasource.remote.converter

import com.weather.datasource.remote.dto.DailyWeatherDto
import com.weather.datasource.remote.dto.HourlyWeatherDto
import com.weather.models.DailyWeather
import com.weather.models.Icon
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object WeatherDataConverterTest {

    @Test
    fun convertHourlyWeather () {
        val hourlyWeatherDto = HourlyWeatherDto(1L, "clear-day", "Ciel nuageux",
            99F, 30F, 25F, 10F,
            0.10F, 0.10F)
        val hourlyWeather = WeatherDataConverter.convert(hourlyWeatherDto)

        Assertions.assertEquals(hourlyWeather.timestamp, 1L)
        Assertions.assertEquals(hourlyWeather.icon, Icon.CLEAR_DAY)
        Assertions.assertEquals(hourlyWeather.summary, "Ciel nuageux")
        Assertions.assertEquals(hourlyWeather.precipProbability, 99F)
        Assertions.assertEquals(hourlyWeather.temperature, 30F)
        Assertions.assertEquals(hourlyWeather.apparentTemperature, 25F)
        Assertions.assertEquals(hourlyWeather.humidity, 10F)
        Assertions.assertEquals(hourlyWeather.windSpeed, 0.10F)
        Assertions.assertEquals(hourlyWeather.cloudCover, 0.10F)
    }

    @Test
    fun convertDailyWeather () {
        val dailyWeatherDto = DailyWeatherDto(1L, "clear-day", "Ciel nuageux", 10F, 20F)
        val dailyWeather = WeatherDataConverter.convert(dailyWeatherDto)

        Assertions.assertEquals(dailyWeather.timestamp, 1L)
        Assertions.assertEquals(dailyWeather.icon, Icon.CLEAR_DAY)
        Assertions.assertEquals(dailyWeather.summary, "Ciel nuageux")
    }
}