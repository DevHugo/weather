package com.weather.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.weather.models.UserPref
import com.weather.today.viewmodel.TodayWeatherViewModel
import com.weather.today.viewmodel.state.TodayWeatherState
import com.weather.today.viewmodel.state.WeatherState
import kotlinx.android.synthetic.main.fragment_today.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val PARIS_LAT = 48.8534f
const val PARIS_LNG = 2.3488f

class TodayFragment : Fragment() {

    private val todayViewModel: TodayWeatherViewModel by viewModel()

    companion object {
        fun newInstance() = TodayFragment()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_today, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPref = UserPref(PARIS_LAT, PARIS_LNG, "fr", "auto")
        todayViewModel.fetchTodayWeather(userPref)
        todayViewModel.observeTodayWeather().observe(viewLifecycleOwner, Observer { weather ->
            if (weather is WeatherState.Perfect) {
                displayTodayWeather(weather.state)
            }
        })

    }

    private fun displayTodayWeather(weather: TodayWeatherState) {
        if (weather.temperature != null)
            tv_today_current_temperature.text = weather.temperature

        if (weather.summary != null)
            tv_today_description.text = weather.summary

        if (weather.minTemperature != null && weather.maxTemperature != null)
            tv_today_temperatures_value.text = "${weather.minTemperature} | ${weather.maxTemperature}"

        if (weather.precipProbability != null)
            tv_today_rainfall_value.text = weather.precipProbability

        if (weather.windSpeed != null)
            tv_today_wind_value.text = weather.windSpeed

        if (weather.cloudCover != null)
            tv_today_cloudcover_value.text = weather.cloudCover
    }
}