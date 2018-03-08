package com.lukasz.myweather;

import com.lukasz.myweather.POJO.WeekWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lukasz on 2018-03-07.
 * Upload Picture
 */

public interface WebService {

    @GET("daily?&APPID=032a76b432cfa81e175c33bc81dae187")
    Call<WeekWeather> getWeather(@Query("id") int cityId );
}
