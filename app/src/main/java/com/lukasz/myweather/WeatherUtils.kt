package com.lukasz.myweather

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi

/**
 * Created by Lukasz on 2018-03-15.
Upload Picture
 */
class WeatherUtils {

    fun KelwinToCelsius(temperatureInKelwin: Double): Double {
        return temperatureInKelwin - 273.15
    }

    fun getStringForWeatherCondition(weatherId: Int): String {
        val stringId: Int
        stringId = when (weatherId) {
            500 -> R.string.condition_500
            501 -> R.string.condition_501
            502 -> R.string.condition_502
            601 -> R.string.condition_601
            611 -> R.string.condition_611
            800 -> R.string.condition_800
            801 -> R.string.condition_801
            803 -> R.string.condition_803

            else -> return "Inna pogoda"
        }
        return stringId.toString()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getIconWeatherCondition (conditionId : Int, context : Context) : Drawable {
        return when (conditionId) {
            in 500..504 -> context.getDrawable(R.drawable.rain)
            800 -> context.getDrawable(R.drawable.clear)
            in 802..804 -> context.getDrawable(R.drawable.cloudy)
            in 600..622 -> context.getDrawable(R.drawable.snow)
            else -> context.getDrawable(R.drawable.abc_ic_clear_material)
        }
    }

}