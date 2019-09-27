package com.weather.weekly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.weather.models.UserPref
import com.weather.weekly.viewmodel.WeeklyWeatherViewModel
import com.weather.weekly.viewmodel.state.WeatherState
import kotlinx.android.synthetic.main.fragment_weekly.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val PARIS_LAT = 48.8534f
const val PARIS_LNG = 2.3488f

class WeeklyFragment : Fragment() {

    private val weaklyViewModel: WeeklyWeatherViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_weekly, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPref = UserPref(PARIS_LAT, PARIS_LNG, "fr", "auto")

        weaklyViewModel.fetchWeeklyWeather(userPref)
        weaklyViewModel.observeWeeklyWeather().observe(viewLifecycleOwner, Observer {
            if (it is WeatherState.Perfect) {
                val adapter = WeeklyAdapter(it.weeklyWeatherState)
                list_weekly.adapter = adapter
                adapter.notifyItemRangeInserted(0, it.weeklyWeatherState.size)
            }
        })

        list_weekly.layoutManager = LinearLayoutManager(context)
        list_weekly.setHasFixedSize(true)
        list_weekly.isNestedScrollingEnabled = false

    }

}