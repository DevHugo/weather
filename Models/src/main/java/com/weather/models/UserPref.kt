package com.weather.models

data class UserPref (
    val lat: Float,
    val long: Float,
    val lang: String,
    val units: String
)