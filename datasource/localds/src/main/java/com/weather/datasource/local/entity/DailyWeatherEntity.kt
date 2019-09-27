package com.weather.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily")
data class DailyWeatherEntity (
    @PrimaryKey
    @ColumnInfo(name ="time")
    val timestamp: Long,
    @ColumnInfo(name ="icon")
    val icon: String?,
    @ColumnInfo(name ="summary")
    val summary: String?)