package com.colvir.homework2_degreesConverter.impl;

import com.colvir.homework2_degreesConverter.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TemperatureConvertServiceImpl implements TemperatureConvertService {

    private final Map<String, TemperatureConverter> temperatureConverters;

    public TemperatureConvertServiceImpl(Map<String, TemperatureConverter> temperatureConverters) {
        this.temperatureConverters = temperatureConverters;
    }

    @Override
    public double convert(String scaleFrom, String scaleTo, double degrees) {

        if (scaleFrom.equals(scaleTo)) {
            return degrees;
        }

        switch (scaleFrom) {
            case "C" -> {
                if (scaleTo.equals("K")) {
                    return temperatureConverters.get("CelsiusToKelvin").convert(degrees, false);
                } else if (scaleTo.equals("F")) {
                    return temperatureConverters.get("CelsiusToFahrenheit").convert(degrees, false);
                } else {
                    throw new IllegalStateException("Unexpected value: " + scaleTo);
                }
            }
            case "K" -> {
                if (scaleTo.equals("C")) {
                    return temperatureConverters.get("CelsiusToKelvin").convert(degrees, true);
                } else if (scaleTo.equals("F")) {
                    return temperatureConverters.get("KelvinToFahrenheit").convert(degrees, false);
                } else {
                    throw new IllegalStateException("Unexpected value: " + scaleTo);
                }
            }
            case "F" -> {
                if (scaleTo.equals("C")) {
                    return temperatureConverters.get("CelsiusToFahrenheit").convert(degrees, true);
                } else if (scaleTo.equals("K")) {
                    return temperatureConverters.get("KelvinToFahrenheit").convert(degrees, true);
                } else {
                    throw new IllegalStateException("Unexpected value: " + scaleTo);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + scaleFrom);
        }
    }
}
