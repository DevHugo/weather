package com.weather.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherDataDto (
    @SerializedName("hourly")
    val houly: MainHourlyWeatherDto?,
    @SerializedName("daily")
    val daily: MainDailyWeatherDto?)

data class MainHourlyWeatherDto (
    @SerializedName("data")
    val data: List<HourlyWeatherDto>?)

data class HourlyWeatherDto (
    @SerializedName("time")
    val timestamp: Long,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("summary")
    val summary: String?,
    @SerializedName("precipProbability")
    val precipProbability: Float?,
    @SerializedName("temperature")
    val temperature: Float?,
    @SerializedName("apparentTemperature")
    val apparentTemperature: Float?,
    @SerializedName("humidity")
    val humidity: Float?,
    @SerializedName("windSpeed")
    val windSpeed: Float?,
    @SerializedName("cloudCover")
    val cloudCover: Float?)

data class MainDailyWeatherDto (
    @SerializedName("data")
    val data: List<DailyWeatherDto>?)

data class DailyWeatherDto (
    @SerializedName("time")
    val timestamp: Long,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("summary")
    val summary: String?,
    @SerializedName("temperatureHigh")
    val temperatureHigh: Float?,
    @SerializedName("temperatureLow")
    val temperatureLow: Float?)