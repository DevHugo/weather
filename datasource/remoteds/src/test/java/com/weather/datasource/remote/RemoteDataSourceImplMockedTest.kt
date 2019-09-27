package com.weather.datasource.remote

import com.weather.datasource.remote.utils.MockInterceptor
import com.weather.datasource.remote.utils.TestUtils
import com.weather.models.Icon
import com.weather.models.RemoteException
import com.weather.models.UserPref
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSourceImplMockedTest {

    private val userPref = UserPref(0F, 0F, "", "")

    @Test
    fun fetchWeatherPerHourForDay() {
        runBlocking {
            val json = TestUtils.readFile("json_200_weather_per_hour")
            val clientHttp = createMockedRetrofit(200, json)
            val result = RemoteDataSourceImpl(clientHttp).fetchWeatherPerHourForDay(0, userPref)

            Assertions.assertEquals(1, result.size)

            val hourlyWeather = result[0]
            Assertions.assertTrue(1570226400L == hourlyWeather.timestamp)
            Assertions.assertEquals(Icon.PARTLY_CLOUDY_NIGHT, hourlyWeather.icon)
            Assertions.assertEquals("Ciel Nuageux", hourlyWeather.summary)
            Assertions.assertEquals(0.13F, hourlyWeather.precipProbability)
            Assertions.assertEquals(14.26F, hourlyWeather.temperature)
            Assertions.assertEquals(14.26F, hourlyWeather.apparentTemperature)
            Assertions.assertEquals(0.81F, hourlyWeather.humidity)
            Assertions.assertEquals(4.07F, hourlyWeather.windSpeed)
            Assertions.assertEquals(0.77F, hourlyWeather.cloudCover)
        }
    }

    @Test
    fun fetchWeatherPerHourForDayMissingField() {
        runBlocking {
            val json = TestUtils.readFile("json_200_weather_per_hour_missing_field")
            val clientHttp = createMockedRetrofit(200, json)
            try {
                RemoteDataSourceImpl(clientHttp).fetchWeatherPerHourForDay(0, userPref)
            } catch (e: Exception) {
                if (e !is RemoteException) {
                    fail("Should have been a RemoteException")
                } else {}
            }
        }
    }

    @Test
    fun fetchWeatherPerDayForNextWeek() {
        runBlocking {
            val json = TestUtils.readFile("json_200_weather_next_week")
            val clientHttp = createMockedRetrofit(200, json)
            val result = RemoteDataSourceImpl(clientHttp).fetchWeatherPerDayForNextWeek(userPref)

            Assertions.assertEquals(1, result.size)

            val hourlyWeather = result[0]
            Assertions.assertTrue(1570226400L == hourlyWeather.timestamp)
            Assertions.assertEquals(Icon.PARTLY_CLOUDY_NIGHT, hourlyWeather.icon)
            Assertions.assertEquals("Ciel Nuageux", hourlyWeather.summary)
        }
    }

    @Test
    fun fetchWeatherPerDayForNextWeekMissingField() {
        runBlocking {
            val json = TestUtils.readFile("json_200_weather_next_week_missing_field")
            val clientHttp = createMockedRetrofit(200, json)
            try {
                RemoteDataSourceImpl(clientHttp).fetchWeatherPerDayForNextWeek(userPref)
            } catch (e: Exception) {
                if (e !is RemoteException) {
                    fail("Should have been a RemoteException")
                } else {}
            }
        }
    }

    private fun createMockedRetrofit(responseCode: Int, json: String): Retrofit {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(MockInterceptor(responseCode, json))
            .build()

        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl("http://fakeurl.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}