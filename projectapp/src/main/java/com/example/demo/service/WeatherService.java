package com.example.demo.service;


import com.example.demo.model.Weather;

import java.util.List;

public interface WeatherService {
    Weather fetchAndSaveWeather(String city, String apiKey);
    List<Weather> getAllWeatherData();
}


