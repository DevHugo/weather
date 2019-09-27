package com.weather.today.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.models.HourlyWeather
import com.weather.models.UserPref
import com.weather.repository.WeatherRepository
import com.weather.today.viewmodel.state.TodayWeatherState
import com.weather.today.viewmodel.state.WeatherState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.roundToInt

class TodayWeatherViewModel (private val repository: WeatherRepository) : ViewModel() {

    private val todayWeatherState = MutableLiveData<WeatherState>()

    fun fetchTodayWeather(userPref: UserPref) {
        val timestamp = getPreviousDayAtMidnight().time.time / 1000L

        viewModelScope.launch(Dispatchers.IO) {
            val weatherPerHourForTheDay = repository.fetchWeatherForDay(timestamp, userPref).sortedBy { it.timestamp }
            val currentWeather = weatherPerHourForTheDay.firstOrNull  { it.timestamp >= timestamp }

            // Min temperature is at 6pm and max temperature is at 18am
            val minTemperature: Float? = weatherPerHourForTheDay.getOrNull(5)?.temperature
            val maxTemperature: Float? = weatherPerHourForTheDay.getOrNull(17)?.temperature

            if (currentWeather != null) {
                todayWeatherState.postValue(WeatherState.Perfect(formatWeather(currentWeather, minTemperature, maxTemperature)))
            } else {
                todayWeatherState.postValue(WeatherState.Error)
            }
        }
    }

    private fun formatWeather(weather: HourlyWeather, minTemperature: Float?, maxTemperature: Float?): TodayWeatherState {
       // TODO adapt with different language and different notation
       return TodayWeatherState(Date(weather.timestamp),
           weather.icon,
           weather.summary,
           weather.precipProbability?.let { ""+ (it * 100f).roundToInt() +" %" },
           ""+weather.temperature?.roundToInt()+"°C",
           minTemperature?.let { "${it.roundToInt()}°" },
           maxTemperature?.let { "${it.roundToInt()}°" },
           ""+weather.windSpeed?.roundToInt() + " km/h",
           weather.cloudCover?.let { ""+ (it * 100f).roundToInt() +" %" })
    }

    private fun getPreviousDayAtMidnight() = GregorianCalendar().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    fun observeTodayWeather() = todayWeatherState
}