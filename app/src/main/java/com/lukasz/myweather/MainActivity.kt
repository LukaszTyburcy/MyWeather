package com.lukasz.myweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.lukasz.myweather.POJO.WeekWeather
import com.lukasz.myweather.adapter.ForecastAdapter
import com.lukasz.myweather.data.AdapterWeatherData
import com.lukasz.myweather.webApi.RetrofitBuilder
import com.lukasz.myweather.webApi.WebService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingIndicatorPB.visibility = View.VISIBLE
    }

    private fun getWeekWeather(call : Call<WeekWeather>, recyclerView: RecyclerView){
        call.enqueue(object : Callback<WeekWeather>{
            override fun onFailure(call: Call<WeekWeather>?, t: Throwable?) {
                Log.d("ERROR", t?.localizedMessage)
            }

            override fun onResponse(call: Call<WeekWeather>?, response: Response<WeekWeather>?) {
                val weatherList = ArrayList(response?.body()?.list)
                val simpleWeather = ArrayList<AdapterWeatherData>()
                (0..6).mapTo(simpleWeather) { AdapterWeatherData(weatherList[it].clouds.toString(), weatherList[it].deg.toString()) }
                loadingIndicatorPB.visibility = View.INVISIBLE
                setMyAdapter(recyclerView,simpleWeather)
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
        getWeekWeather(call,weatherRV)
    }

    private fun setMyAdapter(recyclerView: RecyclerView, list : ArrayList<AdapterWeatherData>){
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val adapter = ForecastAdapter(list)
        recyclerView.adapter = adapter
    }

}
