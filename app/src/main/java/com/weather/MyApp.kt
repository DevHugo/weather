package com.weather

import android.app.Application
import com.weather.datasource.local.localDataSourceModule
import com.weather.datasource.remote.remoteDataSourceModule
import com.weather.repository.repositoryModule
import com.weather.today.viewmodel.viewModelTodayModule
import com.weather.weekly.viewmodel.viewModelWeeklyModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(
                viewModelTodayModule,
                viewModelWeeklyModule,
                repositoryModule,
                remoteDataSourceModule,
                localDataSourceModule
            ))
        }

    }
}