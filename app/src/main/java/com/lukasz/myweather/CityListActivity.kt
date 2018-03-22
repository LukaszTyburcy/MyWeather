package com.lukasz.myweather

import android.R
import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.lukasz.myweather.data.CityData
import kotlinx.android.synthetic.main.activity_city_list.*


/**
 * Created by Lukasz on 2018-03-22.
Upload Picture
 */
class CityListActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lukasz.myweather.R.layout.activity_city_list)
        //val cars = arrayOf("Mercedes", "Fiat", "Ferrari", "Aston Martin", "Lamborghini", "Skoda", "Volkswagen", "Audi", "Citroen")

        val cityname = resources.getStringArray(com.lukasz.myweather.R.array.cityName)
        val cityId = resources.getStringArray(com.lukasz.myweather.R.array.cityName)
        val adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1, cityname)
        val city = arrayOfNulls<CityData>(cityname.size)
        cityLV.adapter = adapter
        for (i in 0..(cityId.size-1)){
                val oneCity = CityData(cityId[i], cityname[i])
                city[i] = oneCity
        }
    }

}