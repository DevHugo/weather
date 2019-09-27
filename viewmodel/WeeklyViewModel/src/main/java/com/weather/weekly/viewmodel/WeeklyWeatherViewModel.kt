package com.weather.weekly.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.models.DailyWeather
import com.weather.models.UserPref
import com.weather.repository.WeatherRepository
import com.weather.weekly.viewmodel.state.DailyWeatherState
import com.weather.weekly.viewmodel.state.WeatherState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class WeeklyWeatherViewModel (private val repository: WeatherRepository) : ViewModel() {

    private val weeklyWeatherState = MutableLiveData<WeatherState>()

    fun fetchWeeklyWeather(userPref: UserPref) {

        viewModelScope.launch(Dispatchers.IO) {
            val weeklyWeather = repository.fetchWeatherForWeek(userPref)

            if (weeklyWeather.isEmpty()) {
                weeklyWeatherState.postValue(WeatherState.Error)
            } else {
                weeklyWeatherState.postValue(WeatherState.Perfect(convert(weeklyWeather)))
            }
        }
    }

    private fun convert (from: List<DailyWeather>) =
        from.map { DailyWeatherState(Date(it.timestamp*1000), it.icon, it.summary) }

    fun observeWeeklyWeather() = weeklyWeatherState
}