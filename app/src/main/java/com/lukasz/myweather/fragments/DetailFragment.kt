package com.lukasz.myweather.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lukasz.myweather.R



/**
 * Created by Lukasz on 2018-03-19.
Upload Picture
 */
class DetailFragment : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.detail_fragment,container,false)
    }

    fun setText(txt: String) {
        val view = view!!.findViewById(R.id.textView) as TextView
        view.text = txt
    }
}