package com.weather.datasource.remote.converter

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
}