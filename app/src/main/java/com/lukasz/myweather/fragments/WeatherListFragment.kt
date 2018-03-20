package com.lukasz.myweather.fragments

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.lukasz.myweather.POJO.WeekWeather
import com.lukasz.myweather.R
import com.lukasz.myweather.WeatherUtils
import com.lukasz.myweather.adapter.ForecastAdapter
import com.lukasz.myweather.data.AdapterWeatherData
import com.lukasz.myweather.webApi.RetrofitBuilder
import com.lukasz.myweather.webApi.WebService
import kotlinx.android.synthetic.main.list_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Lukasz on 2018-03-19.
Upload Picture
 */
class WeatherListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.list_fragment,container,false)
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.refresh -> {refreshWeather()
                return true}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun refreshWeather(){
        val client  = RetrofitBuilder().getMyWebService()
        val call = getDatabodyCall(client,2172797)
        getWeekWeather(call, weatherRV)
    }

    private fun getDatabodyCall(client : WebService, id : Int) : Call<WeekWeather> {
        return client.getWeather(id)
    }

    private fun getWeekWeather(call : Call<WeekWeather>, recyclerView: RecyclerView){
        call.enqueue(object : Callback<WeekWeather> {
            override fun onFailure(call: Call<WeekWeather>?, t: Throwable?) {
                Log.d("ERROR", t?.localizedMessage)
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onResponse(call: Call<WeekWeather>?, response: Response<WeekWeather>?) {
                val weatherList = ArrayList(response?.body()?.list)
                val simpleWeather = ArrayList<AdapterWeatherData>()
                (0..6).mapTo(simpleWeather) { AdapterWeatherData(weatherList[it].weather[0].description, WeatherUtils().KelwinToCelsius(weatherList[it].temp.day).toInt(), WeatherUtils().KelwinToCelsius(weatherList[it].temp.night).toInt(), WeatherUtils().getIconWeatherCondition(weatherList[it].weather[0].id,context), WeatherUtils().setTime(weatherList[it].dt)) }
                //    loadingIndicatorPB.visibility = View.INVISIBLE
                setMyAdapter(recyclerView,simpleWeather)
            }
        })
    }

    private fun setMyAdapter(recyclerView: RecyclerView, list : ArrayList<AdapterWeatherData>){
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)
        val adapter = ForecastAdapter(list)
        recyclerView.adapter = adapter
    }

}