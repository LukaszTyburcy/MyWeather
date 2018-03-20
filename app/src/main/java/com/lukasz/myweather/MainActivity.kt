package com.lukasz.myweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.lukasz.myweather.fragments.WeatherListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // loadingIndicatorPB.visibility = View.VISIBLE
        setWeatherListFragment()
    }

    private fun setWeatherListFragment() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragment = WeatherListFragment()
        transaction.add(R.id.fragmentContainer, fragment).addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

}

