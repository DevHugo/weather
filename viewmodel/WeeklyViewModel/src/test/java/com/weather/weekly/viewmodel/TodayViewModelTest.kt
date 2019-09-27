package com.weather.weekly.viewmodel

import com.jraska.livedata.test
import com.weather.models.DailyWeather
import com.weather.models.Icon
import com.weather.models.UserPref
import com.weather.test.InstantExecutorExtension
import com.weather.weekly.viewmodel.state.WeatherState
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class WeeklyViewModelTest {

    @Test
    fun fetchWeeklyWeatherWithData() {
        val repository = object : MockWeatherRepository {
            override suspend fun fetchWeatherForWeek(userPref: UserPref) = listOf(dailyWeather)
        }

        val vm = WeeklyWeatherViewModel(repository)
        vm.fetchWeeklyWeather(userPref)
        vm.observeWeeklyWeather()
            .test()
            .awaitValue()
            .assertValue {
                return@assertValue it is WeatherState.Perfect &&
                        it.weeklyWeatherState[0].icon == Icon.CLEAR_DAY &&
                        it.weeklyWeatherState[0].summary == "Ciel nuageux"
            }
    }

    @Test
    fun fetchWeeklyWeatherWithNoConnection() {
        val repository = object : MockWeatherRepository {
            override suspend fun fetchWeatherForWeek(userPref: UserPref) = emptyList<DailyWeather>()
        }

        val vm = WeeklyWeatherViewModel(repository)
        vm.fetchWeeklyWeather(userPref)
        vm.observeWeeklyWeather()
            .test()
            .assertValue { it is WeatherState.Error }
    }
}
