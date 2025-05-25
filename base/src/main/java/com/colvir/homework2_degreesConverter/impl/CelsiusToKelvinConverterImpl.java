package com.colvir.homework2_degreesConverter.impl;

import com.colvir.homework2_degreesConverter.TemperatureConverter;
import org.springframework.stereotype.Component;

@Component("CelsiusToKelvin")
public class CelsiusToKelvinConverterImpl implements TemperatureConverter {

    @Override
    public double convert(double degrees, boolean reverse) {
        if (reverse) {
            return degrees - 273;
        } else {
            return degrees + 273;
        }
    }
}
