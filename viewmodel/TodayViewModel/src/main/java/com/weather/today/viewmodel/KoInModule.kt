package com.weather.today.viewmodel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelTodayModule = module {
    viewModel { TodayWeatherViewModel(get()) }
}
