package com.weather.datasource.remote

import com.weather.models.UserPref
import com.weather.repository.RemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

const val PARIS_LAT = 48.8534f
const val PARIS_LNG = 2.3488f

class RemoteDataSourceImplIntegrationTest : KoinTest {

    private val remoteDataSource : RemoteDataSource by inject()

    @BeforeEach
    fun beforeEachTest() {
        startKoin { modules(remoteDataSourceModule) }
    }

    @AfterEach
    fun afterEachTest() {
        stopKoin()
    }

    @Test
    fun fetchWeatherPerHourForDay() {
        val timestamp = System.currentTimeMillis() / 1000L
        runBlocking {
            val weather = remoteDataSource.fetchWeatherPerHourForDay(timestamp, UserPref(PARIS_LAT, PARIS_LNG, "fr", "auto"))
            Assertions.assertEquals(24, weather.size)
        }
    }

    @Test
    fun fetchWeatherPerHourForDayWithIncorrectParameter() {
        val timestamp = System.currentTimeMillis() / 1000L
        runBlocking {
            val badTimestamp = remoteDataSource.fetchWeatherPerHourForDay(-1, UserPref(PARIS_LAT, PARIS_LNG, "fr", "notAUnits"))
            Assertions.assertTrue(badTimestamp.isEmpty())
            val badLat = remoteDataSource.fetchWeatherPerHourForDay(timestamp, UserPref(-100000F, PARIS_LNG, "fr", "auto"))
            Assertions.assertTrue(badLat.isEmpty())
            val badLng = remoteDataSource.fetchWeatherPerHourForDay(timestamp, UserPref(PARIS_LAT, -100000F, "fr", "auto"))
            Assertions.assertTrue(badLng.isEmpty())
            val badLang = remoteDataSource.fetchWeatherPerHourForDay(timestamp, UserPref(PARIS_LAT, PARIS_LNG, "notALang", "auto"))
            Assertions.assertTrue(badLang.isEmpty())
            val badUnits = remoteDataSource.fetchWeatherPerHourForDay(timestamp, UserPref(PARIS_LAT, PARIS_LNG, "fr", "notAUnits"))
            Assertions.assertTrue(badUnits.isEmpty())
        }
    }

    @Test
    fun fetchWeatherPerDayForNextWeek() {
        runBlocking {
            val weather = remoteDataSource.fetchWeatherPerDayForNextWeek(UserPref(PARIS_LAT, PARIS_LNG, "fr", "auto"))
            Assertions.assertEquals(8, weather.size)
        }
    }

    @Test
    fun fetchWeatherPerDayForNextWeekWithIncorrectParameter() {
        val timestamp = System.currentTimeMillis() / 1000L
        runBlocking {
            val badLat = remoteDataSource.fetchWeatherPerDayForNextWeek(UserPref(-100000F, PARIS_LNG, "fr", "auto"))
            Assertions.assertTrue(badLat.isEmpty())
            val badLng = remoteDataSource.fetchWeatherPerDayForNextWeek(UserPref(PARIS_LAT, -100000F, "fr", "auto"))
            Assertions.assertTrue(badLng.isEmpty())
            val badLang = remoteDataSource.fetchWeatherPerDayForNextWeek(UserPref(PARIS_LAT, PARIS_LNG, "notALang", "auto"))
            Assertions.assertTrue(badLang.isEmpty())
            val badUnits = remoteDataSource.fetchWeatherPerDayForNextWeek(UserPref(PARIS_LAT, PARIS_LNG, "fr", "notAUnits"))
            Assertions.assertTrue(badUnits.isEmpty())
        }
    }

}