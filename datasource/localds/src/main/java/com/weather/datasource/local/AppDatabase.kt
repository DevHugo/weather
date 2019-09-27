package com.weather.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weather.datasource.local.entity.*

@Database(entities = [HourlyWeatherEntity::class, DailyWeatherEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weather(): WeatherDao
}
