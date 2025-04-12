package com.colvir.homework2_degreesConverter.impl;

import com.colvir.homework2_degreesConverter.CelsiusToFahrenheitConverter;
import org.springframework.stereotype.Component;

@Component
public class CelsiusToFahrenheitConverterImpl implements CelsiusToFahrenheitConverter {

    @Override
    public double convert(double degrees, boolean reverse) {
        if (!reverse) {
            return degrees * 9/5 + 32;
        } else {
            return (degrees - 32) * 5/9;
        }
    }
}
