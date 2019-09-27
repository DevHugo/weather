package com.weather.today.robot

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import com.weather.test.robot.BaseTestRobot
import com.weather.today.R
import com.weather.today.TodayFragment
import com.weather.today.viewmodel.TodayWeatherViewModel
import com.weather.today.viewmodel.state.TodayWeatherState
import com.weather.today.viewmodel.state.WeatherState
import io.mockk.every
import io.mockk.mockk
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

val currentTemperatureId = R.id.tv_today_current_temperature
 val temperaturesId = R.id.tv_today_temperatures_value
 val rainFallId = R.id.tv_today_rainfall_value
 val todayWindId = R.id.tv_today_wind_value
 val cloudCoverId = R.id.tv_today_cloudcover_value


fun today (state: TodayWeatherState, func: TodayResultRobot.() -> Unit) {
    inject(state)
    val fragmentScenario = launchFragmentInContainer<TodayFragment>()
    fragmentScenario.onFragment {
        TodayResultRobot().apply(func)
    }
    stopKoin()
}

fun inject(state: TodayWeatherState) {
    val module = module {
        viewModel {
            val vm = mockk<TodayWeatherViewModel>()
            every { vm.fetchTodayWeather(any()) } answers {}
            every { vm.observeTodayWeather() } returns  MutableLiveData<WeatherState>().apply {
                postValue(WeatherState.Perfect(state))
            }
            vm
        }
    }

    startKoin {
        modules(module)
    }
}

class TodayResultRobot: BaseTestRobot() {

    fun checkCurrentTemperature() {
    matchText(currentTemperatureId, "20°")
    }

    fun checkMinimalAndMaximalTemperature() {
        matchText(temperaturesId, "14° | 20°")
    }

    fun checkRainFall() {
        matchText(rainFallId, "10%")
    }

    fun checkWind() {
        matchText(todayWindId, "20 km/h")
    }

    fun checkCloudCover() {
        matchText(cloudCoverId, "80%")
    }
}

fun todayInError (func: TodayInErrorResultRobot.() -> Unit) {
    inject()
    val fragmentScenario = launchFragmentInContainer<TodayFragment>()
    fragmentScenario.onFragment { fragment ->
        TodayInErrorResultRobot().apply(func)
    }
    stopKoin()
}

fun inject() {
    val module = module {
        viewModel {
            val vm = mockk<TodayWeatherViewModel>()
            every { vm.fetchTodayWeather(any()) } answers {}
            every { vm.observeTodayWeather() } returns MutableLiveData<WeatherState>().apply {
                postValue(WeatherState.Error)
            }
            vm
        }
    }

    startKoin {
        modules(module)
    }
}

class TodayInErrorResultRobot: BaseTestRobot() {

    fun checkCurrentTemperature() {
        matchText(currentTemperatureId, "X°C")
    }

    fun checkMinimalAndMaximalTemperature() {
        matchText(temperaturesId, "X° | X°")
    }

    fun checkRainFall() {
        matchText(rainFallId, "X%")
    }

    fun checkWind() {
        matchText(todayWindId, "X km/h")
    }

    fun checkCloudCover() {
        matchText(cloudCoverId, "X%")
    }
}