package com.colvir.homework2_degreesConverter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.colvir.homework2_degreesConverter.impl");
        Scanner console = new Scanner(System.in);
        boolean dataError = false;

        System.out.println("Choose a scale from (Celsius - C, Kelvin - K, Fahrenheit - F):");
        String scaleFrom = console.nextLine().toUpperCase();
        if (!scaleFrom.matches("[CKF]")) {
            dataError = true;
        }

        System.out.println("Choose a scale to (Celsius - C, Kelvin - K, Fahrenheit - F):");
        String scaleTo = console.nextLine().toUpperCase();
        if (!scaleTo.matches("[CKF]")) {
            dataError = true;
        }

        double degrees = 0;
        System.out.println("Enter degrees:");
        try {
            degrees = Double.parseDouble(console.nextLine());
        } catch (NumberFormatException e) {
            dataError = true;
        }

        if (!dataError) {
            double resultDegrees = 0;
            switch (scaleFrom) {
                case "C" -> {
                    if (scaleTo.equals("K")) {
                        resultDegrees = applicationContext.getBean(CelsiusToKelvinConverter.class).convert(degrees, false);
                    } else if (scaleTo.equals("F")) {
                        resultDegrees = applicationContext.getBean(CelsiusToFahrenheitConverter.class).convert(degrees, false);
                    }
                }
                case "K" -> {
                    if (scaleTo.equals("C")) {
                        resultDegrees = applicationContext.getBean(CelsiusToKelvinConverter.class).convert(degrees, true);
                    } else if (scaleTo.equals("F")) {
                        resultDegrees = applicationContext.getBean(KelvinToFahrenheitConverter.class).convert(degrees, false);
                    }
                }
                case "F" -> {
                    if (scaleTo.equals("C")) {
                        resultDegrees = applicationContext.getBean(CelsiusToFahrenheitConverter.class).convert(degrees, true);
                    } else if (scaleTo.equals("K")) {
                        resultDegrees = applicationContext.getBean(KelvinToFahrenheitConverter.class).convert(degrees, true);
                    }
                }
            }
            System.out.println(degrees + scaleFrom + " = " + resultDegrees + scaleTo);

        } else {
            System.out.println("wrong input data");
        }
    }
}
