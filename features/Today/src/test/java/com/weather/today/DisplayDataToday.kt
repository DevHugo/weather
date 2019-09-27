package com.weather.today

import androidx.test.filters.LargeTest
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
@LargeTest
class DisplayDataToday : DisplayDataTodayAbstractTest()