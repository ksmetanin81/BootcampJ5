package com.colvir.homework2_degreesConverter.impl;

import com.colvir.homework2_degreesConverter.TemperatureConverter;
import org.springframework.stereotype.Component;

@Component("KelvinToFahrenheit")
public class KelvinToFahrenheitConverterImpl implements TemperatureConverter {

    @Override
    public double convert(double degrees, boolean reverse) {
        if (!reverse) {
            return (degrees - 273) * 9 / 5 + 32;
        } else {
            return (degrees - 32) * 5 / 9 + 273;
        }
    }
}
