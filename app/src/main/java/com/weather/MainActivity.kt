package com.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todayFragment = Navigation.newTodayFragment(supportFragmentManager)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_top, todayFragment).commit()

        val weeklyFragment = Navigation.newWeeklyFragment(supportFragmentManager)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_bottom, weeklyFragment).commit()
    }
}
