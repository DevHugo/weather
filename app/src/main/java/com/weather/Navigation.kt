package com.weather

import androidx.fragment.app.FragmentManager

object Navigation {

    private const val todayFragmentClass = "com.weather.today.TodayFragment"
    private const val weeklyFragmentClass = "com.weather.weekly.WeeklyFragment"

    fun newTodayFragment (supportFragmentManager: FragmentManager) =
        supportFragmentManager.fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), todayFragmentClass)

    fun newWeeklyFragment (supportFragmentManager: FragmentManager) =
        supportFragmentManager.fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), weeklyFragmentClass)

}