package com.weather.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weather.datasource.local.entity.DailyWeatherEntity
import com.weather.datasource.local.entity.HourlyWeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM hourly WHERE time BETWEEN :from AND :to ")
    suspend fun getWeatherPerHour(from: Long, to: Long): List<HourlyWeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHourly(weather: List<HourlyWeatherEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDaily(weather: List<DailyWeatherEntity>)

    @Query("SELECT * FROM daily WHERE time BETWEEN :from AND :to ")
    fun getWeatherPerDay(from: Long, to: Long): List<DailyWeatherEntity>
}
