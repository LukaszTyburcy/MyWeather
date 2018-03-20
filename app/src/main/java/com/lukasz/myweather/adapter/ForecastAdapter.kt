package com.lukasz.myweather.adapter

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lukasz.myweather.R
import com.lukasz.myweather.data.AdapterWeatherData
import com.lukasz.myweather.fragments.DetailFragment


/**
 * Created by Lukasz on 2018-03-08.
Upload Picture
 */
class ForecastAdapter(val list:ArrayList<AdapterWeatherData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if(holder is ForecastAdapterItemViewHolder){
            holder.bindItems(list[position])
        } else if(holder is ForecastAdapterHeaderViewHolder){
            holder.bindItems(list[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        if(viewType == TYPE_HEADER) {val v = LayoutInflater.from(parent?.context).inflate(R.layout.header_one_day_item,parent,false)
            return ForecastAdapterHeaderViewHolder(v)}
        if(viewType == TYPE_ITEM) {val v = LayoutInflater.from(parent?.context).inflate(R.layout.one_day_item,parent,false)
            return ForecastAdapterItemViewHolder(v)}
        throw RuntimeException("there is no type that matches the type $viewType + make sure your using types correctly")
    }


    class ForecastAdapterItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(data: AdapterWeatherData){
            val description: TextView = itemView.findViewById(R.id.weatherDescItemTV)
            val temperatureDay: TextView = itemView.findViewById(R.id.tempDayItemTV)
            val temperatureNight: TextView = itemView.findViewById(R.id.tempNightItemTV)
            val weatherIcon: ImageView = itemView.findViewById(R.id.weatherDayIV)
            val time: TextView = itemView.findViewById(R.id.timeTV)
            description.text = data.weatherDecription
            temperatureDay.text = data.dayTemperature.toString()
            temperatureNight.text = data.nightTemperture.toString()
            weatherIcon.setImageDrawable(data.weatherIcon)
            time.text = data.time

            itemView.setOnClickListener{
                val activity = itemView.context as AppCompatActivity
                val myFragment = DetailFragment()
                activity.supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, myFragment).addToBackStack(null).commit()
            }
        }

    }
    class ForecastAdapterHeaderViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(data: AdapterWeatherData){
            val description: TextView = itemView.findViewById(R.id.weatherDescTV)
            val temperatureDay: TextView = itemView.findViewById(R.id.dayTempTV)
            val temperatureNight: TextView = itemView.findViewById(R.id.nightTempTV)
            val weatherIcon: ImageView = itemView.findViewById(R.id.weatherDayIV)
            description.text = data.weatherDecription
            temperatureDay.text = data.dayTemperature.toString()
            temperatureNight.text = data.nightTemperture.toString()
            weatherIcon.setImageDrawable(data.weatherIcon)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(isPositionHeader(position))
            return TYPE_HEADER
        return TYPE_ITEM
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }


}