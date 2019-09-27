package com.weather.datasource.local.converter

import com.weather.models.Icon

object IconConverter {

    fun convert (from: String?) =
        when (from) {
            "clear-day" -> Icon.CLEAR_DAY
            "clear-night" -> Icon.CLEAR_NIGHT
            "rain" -> Icon.RAIN
            "snow" -> Icon.SNOW
            "sleet" -> Icon.SLEET
            "wind" -> Icon.WIND
            "fog" -> Icon.FOG
            "cloudy" -> Icon.CLOUDY
            "partly-cloudy-day" -> Icon.PARTLY_CLOUDY_DAY
            "partly-cloudy-night" -> Icon.PARTLY_CLOUDY_NIGHT
            else -> Icon.OTHER
        }

    fun convert (from: Icon?): String? =
        when (from) {
            Icon.CLEAR_DAY -> "clear-day"
            Icon.CLEAR_NIGHT -> "clear-night"
            Icon.RAIN -> "rain"
            Icon.SNOW -> "snow"
            Icon.SLEET -> "sleet"
            Icon.WIND -> "wind"
            Icon.FOG -> "fog"
            Icon.CLOUDY -> "cloudy"
            Icon.PARTLY_CLOUDY_DAY -> "partly-cloudy-day"
            Icon.PARTLY_CLOUDY_NIGHT -> "partly-cloudy-night"
            else -> null
        }
}