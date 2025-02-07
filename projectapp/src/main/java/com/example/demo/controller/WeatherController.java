package com.example.demo.controller;

import com.example.demo.model.Weather;
import com.example.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    private final String API_KEY = "8bf78da97ec68d004392040ff0d8e2a1";

    @GetMapping("/{city}")
    public Weather getWeather(@PathVariable String city) {
        return weatherService.fetchAndSaveWeather(city, API_KEY);
    }

    @GetMapping("/all")
    public Object getAllWeatherData() {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) (List<?>) weatherService.getAllWeatherData();
        return weatherList.stream()
                .sorted(Comparator.comparing(weather -> (Comparable) weather.get("timestamp"))) // Ensure timestamp is Comparable
                .collect(Collectors.toList());
    }


    }



