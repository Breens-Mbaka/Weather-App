package com.moringaschool.weatherapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.weatherapp.R;
import com.moringaschool.weatherapp.adapters.RecyclerViewAdapter;
import com.moringaschool.weatherapp.models.DailyWeather;
import com.moringaschool.weatherapp.networking.OpenWeatherClient;
import com.moringaschool.weatherapp.networking.OpenWeatherInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.search) ImageView search;
    @BindView(R.id.editTextTextPersonName) EditText mLocation;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mLocation.getText().toString();
                if(location.equals("")) {
                    Toast.makeText(MainActivity.this, "Please input city", Toast.LENGTH_LONG).show();
                }
                else{
                    getWeatherData(location);
                }
            }
        });

    }

    private void getWeatherData(String location) {
        OpenWeatherInterface openWeatherInterface = OpenWeatherClient.getClient().create(OpenWeatherInterface.class);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Call<DailyWeather> call = openWeatherInterface.getWeather(location);
        call.enqueue(new Callback<DailyWeather>() {
            @Override
            public void onResponse(Call<DailyWeather> call, Response<DailyWeather> response) {
                recyclerViewAdapter = new RecyclerViewAdapter(response.body().getList());
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<DailyWeather> call, Throwable t) {
                Log.e("ERROR", "Error is: " + t);
            }
        });
    }
}