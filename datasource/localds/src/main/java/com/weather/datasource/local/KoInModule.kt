package com.weather.datasource.local

import com.weather.repository.LocalDataSource
import org.koin.dsl.module

val localDataSourceModule = module {
    single<LocalDataSource> { LocalDataSourceImpl(get()) }
}
