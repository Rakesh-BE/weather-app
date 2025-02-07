package com.example.demo.service;

import com.example.demo.model.Weather;
import com.example.demo.repository.WeatherRepository;
import com.example.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric";

    @Override
    public Weather fetchAndSaveWeather(String city, String apiKey) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class, city, apiKey);

        Weather weather = new Weather();
        weather.setCity(city);
        weather.setDescription(((Map<String, String>) ((List<?>) response.get("weather")).get(0)).get("description"));
        weather.setTemperature(Double.parseDouble(((Map<?, ?>) response.get("main")).get("temp").toString()));
        weather.setTimestamp(LocalDateTime.now());

        return weatherRepository.save(weather);
    }

    @Override
    public List<Weather> getAllWeatherData() {
        return weatherRepository.findAll();
    }
}



