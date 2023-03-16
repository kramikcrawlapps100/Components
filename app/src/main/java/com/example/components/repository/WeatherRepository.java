package com.example.components.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.components.RetrofitClient;
import com.example.components.interfaces.WeatherApiService;
import com.example.components.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository{
    private WeatherApiService weatherApiService;
    private double temperature;
    private Weather weather;

    public WeatherRepository() {
        weatherApiService = RetrofitClient.getRetrofitInstance().create(WeatherApiService.class);
    }

    public Weather getWeather(String cityName, String apiKey) {
        Call<Weather> call = weatherApiService.getWeather(cityName, apiKey);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Weather weatherData = response.body();
                    weather = weatherData;
                    temperature = weather.getMain().getTemp();
                    Log.d("kramik",String.valueOf(weather.getMain().getTemp()));
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e("WeatherRepository", "Failed to get weather data: " + t.getMessage());
            }
        });
        return weather;
    }
}



