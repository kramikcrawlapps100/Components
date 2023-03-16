package com.example.components.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.components.model.Weather;
import com.example.components.repository.WeatherRepository;

import java.util.List;

public class WeatherViewModel extends ViewModel {
    private WeatherRepository weatherRepository;

    public WeatherViewModel() {
        this.weatherRepository = new WeatherRepository();
    }

    public Weather getWeather(String cityName, String apiKey) {
        return weatherRepository.getWeather(cityName, apiKey);
    }
}


