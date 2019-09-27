package com.weather.weekly.viewmodel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelWeeklyModule = module {
    viewModel { WeeklyWeatherViewModel(get()) }
}
