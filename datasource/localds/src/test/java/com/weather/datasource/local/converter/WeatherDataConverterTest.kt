package com.weather.datasource.local.converter

import com.weather.datasource.local.entity.DailyWeatherEntity
import com.weather.datasource.local.entity.HourlyWeatherEntity
import com.weather.models.DailyWeather
import com.weather.models.HourlyWeather
import com.weather.models.Icon
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object WeatherDatabaseConverterTest {

    @Test
    fun convertHourlyWeatherEntity () {
        val hourlyWeatherEntity = HourlyWeatherEntity(1L, "clear-day", "Ciel nuageux",
            99F, 30F, 25F, 10F,
            0.10F, 0.10F)

        val hourlyWeather = WeatherDatabaseConverter().convert(hourlyWeatherEntity)

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
    fun convertDailyWeatherEntity () {
        val dailyWeatherEntity = DailyWeatherEntity(1L, "clear-day", "Ciel nuageux")
        val dailyWeather = WeatherDatabaseConverter().convert(dailyWeatherEntity)

        Assertions.assertEquals(dailyWeather.timestamp, 1L)
        Assertions.assertEquals(dailyWeather.icon, Icon.CLEAR_DAY)
        Assertions.assertEquals(dailyWeather.summary, "Ciel nuageux")
    }

    @Test
    fun convertHourlyWeather () {
        val hourlyWeather= HourlyWeather(1L, Icon.CLEAR_DAY, "Ciel nuageux",
            99F, 30F, 25F, 10F,
            0.10F, 0.10F)

        val hourlyWeatherEntity = WeatherDatabaseConverter().convert(hourlyWeather)

        Assertions.assertEquals(hourlyWeatherEntity.timestamp, 1L)
        Assertions.assertEquals(hourlyWeatherEntity.icon, Icon.CLEAR_DAY)
        Assertions.assertEquals(hourlyWeatherEntity.summary, "Ciel nuageux")
        Assertions.assertEquals(hourlyWeatherEntity.precipProbability, 99F)
        Assertions.assertEquals(hourlyWeatherEntity.temperature, 30F)
        Assertions.assertEquals(hourlyWeatherEntity.apparentTemperature, 25F)
        Assertions.assertEquals(hourlyWeatherEntity.humidity, 10F)
        Assertions.assertEquals(hourlyWeatherEntity.windSpeed, 0.10F)
        Assertions.assertEquals(hourlyWeatherEntity.cloudCover, 0.10F)
    }

    @Test
    fun convertDailyWeather () {
        val dailyWeather = DailyWeather(1L, Icon.CLEAR_DAY, "Ciel nuageux")
        val dailyWeatherEntity = WeatherDatabaseConverter().convert(dailyWeather)

        Assertions.assertEquals(dailyWeatherEntity.timestamp, 1L)
        Assertions.assertEquals(dailyWeatherEntity.icon, Icon.CLEAR_DAY)
        Assertions.assertEquals(dailyWeatherEntity.summary, "Ciel nuageux")
    }
}