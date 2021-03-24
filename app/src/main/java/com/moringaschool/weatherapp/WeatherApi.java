package com.moringaschool.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather?appid=8bccb37daae00f1a7a9fdf314206e587&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);
}
