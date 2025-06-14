package com.colvir.dto;

import lombok.Data;

@Data
public class WeatherDto {

    private String location;
    private double temperature;
    private double pressure;
    private int humidity;
    private String description;
}
