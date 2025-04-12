package com.colvir.homework2_degreesConverter.impl;

import com.colvir.homework2_degreesConverter.CelsiusToKelvinConverter;
import org.springframework.stereotype.Component;

@Component
public class CelsiusToKelvinConverterImpl implements CelsiusToKelvinConverter {

    @Override
    public double convert(double degrees, boolean reverse) {
        if (!reverse) {
            return degrees + 273;
        } else {
            return degrees - 273;
        }
    }
}
