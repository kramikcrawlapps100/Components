package com.example.components.interfaces;

import com.example.components.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {

    @GET("weather")
    Call<Weather> getWeather(@Query("q") String cityName, @Query("appid") String apiKey);
}
