package com.weather.datasource.remote

import com.weather.datasource.remote.converter.WeatherDataConverter
import com.weather.datasource.remote.dto.WeatherDataDto
import com.weather.models.DailyWeather
import com.weather.models.HourlyWeather
import com.weather.models.RemoteException
import com.weather.models.UserPref
import com.weather.repository.RemoteDataSource
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val SERVER_URL = "https://api.darksky.net"
const val SECRET_KEY = "6b8664a136a9a39dbf36a2a9971969c7"

interface WeatherDataService {
    @GET("forecast/{apikey}/{lat},{lon},{timestamp}?exclude=currently,minutely,daily,alerts,flags")
    suspend fun fetchWeatherPerHourForDay(@Path("apikey") apikey: String,
                               @Path("lat") lat: Float,
                               @Path("lon") long: Float,
                               @Path("timestamp") timestamp: Long,
                               @Query("lang") lang: String,
                               @Query("units") units: String ): Response<WeatherDataDto>

    @GET("forecast/{apikey}/{lat},{lon}?exclude=currently,minutely,hourly,alerts,flags")
    suspend fun fetchWeatherPerDayForNextWeek(@Path("apikey") apikey: String,
                               @Path("lat") lat: Float,
                               @Path("lon") long: Float,
                               @Query("lang") lang: String,
                               @Query("units") units: String ): Response<WeatherDataDto>
}

class RemoteDataSourceImpl (private val retrofit: Retrofit) : RemoteDataSource {

    override suspend fun fetchWeatherPerHourForDay(timestamp: Long, userPref: UserPref): List<HourlyWeather> {
        val service = retrofit.create(WeatherDataService::class.java)

        return try {
            val weatherDataDto = service.fetchWeatherPerHourForDay(SECRET_KEY, userPref.lat, userPref.long, timestamp, userPref.lang, userPref.units)
            if (weatherDataDto.isSuccessful)
                weatherDataDto.body()?.houly?.data?.map { weatherDto -> WeatherDataConverter.convert(weatherDto) } ?: listOf()
            else
                listOf()
        } catch (e: Exception) {
            throw RemoteException()
        }
    }

    override suspend fun fetchWeatherPerDayForNextWeek(userPref: UserPref): List<DailyWeather> {
        val service = retrofit.create(WeatherDataService::class.java)

        return try {
            val weatherDataDto = service.fetchWeatherPerDayForNextWeek(SECRET_KEY, userPref.lat, userPref.long, userPref.lang, userPref.units)
            if (weatherDataDto.isSuccessful)
                weatherDataDto.body()?.daily?.data?.map { weatherDto -> WeatherDataConverter.convert(weatherDto) } ?: listOf()
            else
                listOf()
        } catch (e: Exception) {
            throw RemoteException()
        }
    }
}