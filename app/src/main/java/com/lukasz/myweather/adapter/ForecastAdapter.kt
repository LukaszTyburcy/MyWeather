package com.lukasz.myweather.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lukasz.myweather.R
import com.lukasz.myweather.data.AdapterWeatherData

/**
 * Created by Lukasz on 2018-03-08.
Upload Picture
 */
class ForecastAdapter(val list:ArrayList<AdapterWeatherData>) : RecyclerView.Adapter<ForecastAdapter.ForecastAdapterItemViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ForecastAdapterItemViewHolder?, position: Int) {
        holder?.bindItems((list[position]))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastAdapterItemViewHolder {
        if(viewType == TYPE_HEADER) {val v = LayoutInflater.from(parent?.context).inflate(R.layout.one_day_item,parent,false)
            return ForecastAdapterItemViewHolder(v)}
        if(viewType == TYPE_ITEM) {val v = LayoutInflater.from(parent?.context).inflate(R.layout.one_day_item,parent,false)
            return ForecastAdapterItemViewHolder(v)}
        throw RuntimeException("there is no type that matches the type $viewType + make sure your using types correctly")
    }


    class ForecastAdapterItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(data: AdapterWeatherData){
            val description: TextView = itemView.findViewById(R.id.weatherDescItemTV)
            val temperatureDay: TextView = itemView.findViewById(R.id.tempDayItemTV)
            val temperatureNight: TextView = itemView.findViewById(R.id.tempNightItemTV)
            val weatherIcon: ImageView = itemView.findViewById(R.id.weatherIV)
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