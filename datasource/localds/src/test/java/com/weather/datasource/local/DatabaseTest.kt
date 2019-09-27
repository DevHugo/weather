package com.weather.datasource.local

import androidx.test.filters.LargeTest
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
@LargeTest
class DatabaseTest : AbstractDatabaseTest()
