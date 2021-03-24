package com.moringaschool.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.search) ImageView search;
    @BindView(R.id.textView3) TextView text1;
    @BindView(R.id.textView4) TextView text2;
    @BindView(R.id.textView6) TextView text6;
    @BindView(R.id.textView5) TextView text3;
    @BindView(R.id.editTextTextPersonName) EditText location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //call api
                getWeatherData(location.getText().toString().trim());
                text6.setText(location.getText().toString());
                location.setText("");
            }
        });
    }

    private void getWeatherData(String name) {
        WeatherApi weatherApi = WeatherClient.getClient().create(WeatherApi.class);

        Call<Example> call = weatherApi.getWeatherData(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                text1.setText("Temperature: " + response.body().getMain().getTemp() + " Celcius");
                text2.setText("Feels like: " + response.body().getMain().getFeels_like() + " Celcius");
                text3.setText("Humidity: " + response.body().getMain().getHumidity() + " %");
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}