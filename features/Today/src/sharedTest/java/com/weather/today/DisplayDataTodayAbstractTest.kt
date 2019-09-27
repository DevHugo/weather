package com.weather.today

import com.weather.models.Icon
import com.weather.today.robot.today
import com.weather.today.robot.todayInError
import com.weather.today.viewmodel.state.TodayWeatherState
import org.junit.Ignore
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.util.*

@Ignore("Can be launch with Espresso or Robolectric")
open class DisplayDataTodayAbstractTest : KoinTest {

    @Test
    fun displayDataToday() {
        val todayState = TodayWeatherState(
            Date(), Icon.CLEAR_DAY, "Ciel nuageux",
            "10%", "20°", "14°", "20°",
            "20 km/h", "80%")
        
        today(todayState) {
            checkCurrentTemperature()
            checkMinimalAndMaximalTemperature()
            checkRainFall()
            checkWind()
            checkCloudCover()
        }
    }

    @Test
    fun displayErrorDataToday() {
        todayInError {
            checkCurrentTemperature()
            checkMinimalAndMaximalTemperature()
            checkRainFall()
            checkWind()
            checkCloudCover()
        }
    }
}