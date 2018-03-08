package com.lukasz.myweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.lukasz.myweather.POJO.WeekWeather
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getWeekWeather(call : Call<WeekWeather>, tempTV : TextView){
        call.enqueue(object : Callback<WeekWeather>{
            override fun onFailure(call: Call<WeekWeather>?, t: Throwable?) {
                Log.d("ERROR", t?.localizedMessage)
            }

            override fun onResponse(call: Call<WeekWeather>?, response: Response<WeekWeather>?) {
                val weatherList = ArrayList(response?.body()?.list)
                val listSize = weatherList.size
                for (i in 0 until listSize) {
                    tempTV.append(weatherList[i].speed.toString() + "\n\n\"")
                }
            }
        })

    }

    private fun getDatabodyCall(client : WebService, id : Int) : Call<WeekWeather>{
        return client.getWeather(id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.refresh -> refreshWeather()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun refreshWeather(){
        val client  = RetrofitBuilder().getMyWebService()
        val call = getDatabodyCall(client,2172797)
        getWeekWeather(call,tempTV)
    }

}
