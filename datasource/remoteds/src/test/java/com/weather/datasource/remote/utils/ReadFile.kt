package com.weather.datasource.remote.utils

import java.io.File

object TestUtils {
    fun readFile(filename: String) =
        File(javaClass.classLoader?.getResource(filename)?.file ?: "").readText()
}
