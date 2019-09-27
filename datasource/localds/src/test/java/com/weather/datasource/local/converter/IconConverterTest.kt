package com.weather.datasource.local.converter

import com.weather.datasource.local.converter.IconConverter
import com.weather.models.Icon
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class IconConverterTest {

    @Test
    fun convertIcon() {
       Assertions.assertEquals(Icon.CLEAR_DAY, IconConverter.convert("clear-day"))
       Assertions.assertEquals(Icon.CLEAR_NIGHT, IconConverter.convert("clear-night"))
       Assertions.assertEquals(Icon.RAIN, IconConverter.convert("rain"))
       Assertions.assertEquals(Icon.SNOW, IconConverter.convert("snow"))
       Assertions.assertEquals(Icon.SLEET, IconConverter.convert("sleet"))
       Assertions.assertEquals(Icon.WIND, IconConverter.convert("wind"))
       Assertions.assertEquals(Icon.FOG, IconConverter.convert("fog"))
       Assertions.assertEquals(Icon.CLOUDY, IconConverter.convert("cloudy"))
       Assertions.assertEquals(Icon.PARTLY_CLOUDY_DAY, IconConverter.convert("partly-cloudy-day"))
       Assertions.assertEquals(Icon.PARTLY_CLOUDY_NIGHT, IconConverter.convert("partly-cloudy-night"))
    }
}