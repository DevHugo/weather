package com.weather.repository

import org.koin.dsl.module

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
}
