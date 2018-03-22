package com.lukasz.myweather

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.lukasz.myweather.fragments.WeatherListFragment


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#303F9F")))
        //loadingIndicatorPB.visibility = View.VISIBLE
        setListFragment()
    }

    private fun setListFragment() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val listFragment = WeatherListFragment()
        ft.add(R.id.fragmentContainer, listFragment).commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

}

