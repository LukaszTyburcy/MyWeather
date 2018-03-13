package com.lukasz.myweather.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lukasz.myweather.R
import com.lukasz.myweather.data.AdapterWeatherData

/**
 * Created by Lukasz on 2018-03-08.
Upload Picture
 */
class ForecastAdapter(val list:ArrayList<AdapterWeatherData>) : RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ForecastAdapterViewHolder?, position: Int) {
        holder?.bindItems(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastAdapterViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.one_day_item,parent,false)
        return ForecastAdapterViewHolder(v)
    }


    class ForecastAdapterViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(data : AdapterWeatherData){
            val dayDate: TextView = itemView.findViewById(R.id.cityTV)
            val temperature: TextView = itemView.findViewById(R.id.temperatureTV)
            dayDate.text = data.dayDate
            temperature.text = data.dayTemperature
        }
    }
}