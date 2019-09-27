package com.weather.datasource.remote

import com.weather.repository.RemoteDataSource
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteDataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single {
        Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
