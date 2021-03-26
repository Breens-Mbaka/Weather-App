package com.moringaschool.weatherapp.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moringaschool.weatherapp.Constants.WEATHER_BASE_URL;

public class OpenWeatherClient {
    private static Retrofit retrofit;
    private static Gson gson;

    public static synchronized Retrofit getClient() {
        if(retrofit == null) {
            if(gson == null) {
               gson = new GsonBuilder().setLenient().create();
            }
            retrofit = new Retrofit.Builder()
                    .baseUrl(WEATHER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
