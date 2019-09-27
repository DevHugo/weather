package com.weather.today.viewmodel

import com.jraska.livedata.test
import com.weather.models.*
import com.weather.test.InstantExecutorExtension
import com.weather.today.viewmodel.state.WeatherState
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class TodayViewModelTest {

    @Test
    fun fetchTodayWeatherWithData() {
        val repository = object : MockWeatherRepository {
            override suspend fun fetchWeatherForDay(from: Long, userPref: UserPref): MutableList<HourlyWeather> {
                return mutableListOf<HourlyWeather>().apply {
                    for (i in 0..24) {
                        add(hourlyWeather)
                    }
                }
            }
        }

        val vm = TodayWeatherViewModel(repository)
        vm.fetchTodayWeather(userPref)
        vm.observeTodayWeather()
            .test()
            .awaitValue()
            .assertValue {
                return@assertValue it is WeatherState.Perfect &&
                        it.state.icon == Icon.CLEAR_DAY &&
                        it.state.summary == "Ciel nuageux" &&
                        it.state.precipProbability == "99 %" &&
                        it.state.temperature == "30°C" &&
                        it.state.minTemperature == "30°" &&
                        it.state.maxTemperature == "30°" &&
                        it.state.windSpeed == "10 km/h" &&
                        it.state.cloudCover == "10 %"
            }
    }

    @Test
    fun fetchTodayWeatherWithPartialResult() {
        val repository = object : MockWeatherRepository {
            override suspend fun fetchWeatherForDay(from: Long, userPref: UserPref): MutableList<HourlyWeather> {
                return mutableListOf(hourlyWeather)
            }
        }

        val vm = TodayWeatherViewModel(repository)
        vm.fetchTodayWeather(userPref)
        vm.observeTodayWeather()
            .test()
            .awaitValue()
            .assertValue {
                return@assertValue it is WeatherState.Perfect &&
                        it.state.icon == Icon.CLEAR_DAY &&
                        it.state.summary == "Ciel nuageux" &&
                        it.state.precipProbability == "99 %" &&
                        it.state.temperature == "30°C" &&
                        it.state.minTemperature == null &&
                        it.state.maxTemperature == null &&
                        it.state.windSpeed == "10 km/h" &&
                        it.state.cloudCover == "10 %"
            }
    }

    @Test
    fun fetchTodayWeatherWithIncorrectData() {
        val repository = object : MockWeatherRepository {
            override suspend fun fetchWeatherForDay(from: Long, userPref: UserPref) = listOf<HourlyWeather>(
                hourlyWeather.copy(timestamp = 0L))
        }

        val vm = TodayWeatherViewModel(repository)
        vm.fetchTodayWeather(userPref)
        vm.observeTodayWeather()
            .test()
            .assertValue { it is WeatherState.Error }
    }

    @Test
    fun fetchTodayWeatherWithNoConnection() {
        val repository = object : MockWeatherRepository {
            override suspend fun fetchWeatherForDay(from: Long, userPref: UserPref) = listOf<HourlyWeather>()
        }

        val vm = TodayWeatherViewModel(repository)
        vm.fetchTodayWeather(userPref)
        vm.observeTodayWeather()
            .test()
            .assertNoValue()
    }
}
