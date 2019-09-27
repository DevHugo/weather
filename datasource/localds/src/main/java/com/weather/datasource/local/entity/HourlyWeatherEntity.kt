package com.weather.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hourly")
data class HourlyWeatherEntity (
    @PrimaryKey
    @ColumnInfo(name ="time")
    val timestamp: Long,
    @ColumnInfo(name ="icon")
    val icon: String?,
    @ColumnInfo(name ="summary")
    val summary: String?,
    @ColumnInfo(name ="precip_probability")
    val precipProbability: Float?,
    @ColumnInfo(name ="temperature")
    val temperature: Float?,
    @ColumnInfo(name ="apparent_temperature")
    val apparentTemperature: Float?,
    @ColumnInfo(name = "humidity")
    val humidity: Float?,
    @ColumnInfo(name = "wind_speed")
    val windSpeed: Float?,
    @ColumnInfo(name = "cloud_cover")
    val cloudCover: Float?)