package com.lukasz.myweather.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukasz.myweather.R


/**
 * Created by Lukasz on 2018-03-19.
Upload Picture
 */
class DetailFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_detail,container,false)

    }

    fun getWeatherData(){

    }
}