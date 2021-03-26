package com.moringaschool.weatherapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public List<com.moringaschool.weatherapp.models.List> dailyWeatherList;

    public RecyclerViewAdapter(List<com.moringaschool.weatherapp.models.List> dailyWeatherList) {
        this.dailyWeatherList = dailyWeatherList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_days, parent,false);
        return new MyViewHolder(view);
    }

    //loads items
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.text3.setText("Temperature: " + dailyWeatherList.get(position).getMain().getTemp().toString());
        holder.text4.setText("Humidity: "+ dailyWeatherList.get(position).getMain().getHumidity().toString());
        holder.text5.setText("Feels like: "+ dailyWeatherList.get(position).getMain().getFeelsLike().toString());
        holder.text7.setText("Max temp: " + dailyWeatherList.get(position).getMain().getTempMax().toString());
        holder.text8.setText("Min temp: " + dailyWeatherList.get(position).getMain().getTempMin().toString());
        holder.text9.setText("Pressure: " + dailyWeatherList.get(position).getMain().getPressure().toString());
        holder.text6.setText("Date:" + dailyWeatherList.get(position).getDtTxt());
    }

    //keeps count of the items in our recyclerview
    @Override
    public int getItemCount() {
        return dailyWeatherList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView3) TextView text3;
        @BindView(R.id.textView4) TextView text4;
        @BindView(R.id.textView5) TextView text5;
        @BindView(R.id.textView7) TextView text7;
        @BindView(R.id.textView8) TextView text8;
        @BindView(R.id.textView9) TextView text9;
        @BindView(R.id.textView6) TextView text6;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
