package com.moringaschool.weatherapp.networking;

import com.moringaschool.weatherapp.models.DailyWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherInterface {
    //GOAL:possible api call
    //wraps response in call object of expected return
    @GET("forecast?appid=8bccb37daae00f1a7a9fdf314206e587&units=metric")
    Call<DailyWeather> getWeather(@Query("q") String location);
}
