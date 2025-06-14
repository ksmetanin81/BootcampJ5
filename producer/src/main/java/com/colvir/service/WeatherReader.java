package com.colvir.service;

import com.colvir.dto.WeatherDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class WeatherReader {

    private static final String API_KEY = "dbcc2471ec56024c2a59db98fb7fcc40";
    private static final String LOCATION = "Kirov,RU"; //Moscow,RU
    private static final String URL_STRING = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    private static final double KELVIN_DIFF = 273.15;
    private static final double GPASCAL_MULT = 0.7500615;

    private WeatherDto parseResponse(String response) throws ParseException {
        JSONObject responseJsonObject = (JSONObject) JSONValue.parseWithException(response);
        JSONArray weatherJsonArray = (JSONArray) responseJsonObject.get("weather");
        JSONObject weatherJsonObject = (JSONObject) weatherJsonArray.get(0);
        JSONObject mainJsonObject = (JSONObject) responseJsonObject.get("main");

        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setLocation(responseJsonObject.get("name").toString());
        weatherDto.setTemperature(Double.parseDouble(mainJsonObject.get("temp").toString()) - KELVIN_DIFF);
        weatherDto.setPressure(Double.parseDouble(mainJsonObject.get("pressure").toString()) * GPASCAL_MULT);
        weatherDto.setHumidity(Integer.parseInt(mainJsonObject.get("humidity").toString()));
        weatherDto.setDescription(weatherJsonObject.get("description").toString());
        return weatherDto;
    }

    public WeatherDto read() throws IOException, ParseException {
        URL url = new URL(URL_STRING.formatted(LOCATION, API_KEY));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        StringBuilder response = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                response.append(str);
            }
        }

        return parseResponse(response.toString());
    }
}
