package com.weather.datasource.local

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.weather.datasource.local.entity.DailyWeatherEntity
import com.weather.datasource.local.entity.HourlyWeatherEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.*

@Ignore("Can be launch with Espresso or Robolectric")
open class AbstractDatabaseTest {

    private lateinit var weatherDatabase: AppDatabase

    @Before
    fun initDb() {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        weatherDatabase = Room.inMemoryDatabaseBuilder(targetContext, AppDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        weatherDatabase.close()
    }

    @Test
    fun saveAndGetHourlyWeather() {
        val hourlyWeatherEntity = HourlyWeatherEntity(1L, "clear-day", "Ciel nuageux",
            99F, 30F, 25F, 10F,
            0.10F, 0.10F)

        runBlocking(Dispatchers.IO) {
            weatherDatabase.weather().insertAllHourly(listOf(hourlyWeatherEntity))
            val weathers = weatherDatabase.weather().getWeatherPerHour(0, 2)
            Assert.assertEquals("", 1, weathers.size)
        }
    }

    @Test
    fun saveAndGetDailyWeather() {
        val dailyWeatherEntity = DailyWeatherEntity(1L, "clear-day",
            "Ciel nuageux")

        runBlocking(Dispatchers.IO)  {
            weatherDatabase.weather().insertAllDaily(listOf(dailyWeatherEntity))
            val weathers = weatherDatabase.weather().getWeatherPerDay(0, 2)
            Assert.assertEquals("", 1, weathers.size)
        }
    }
}