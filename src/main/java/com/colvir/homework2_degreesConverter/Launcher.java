package com.colvir.homework2_degreesConverter;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {

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
            try (ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class)) {
                double resultDegrees = applicationContext.getBean(TemperatureConvertService.class).convert(scaleFrom, scaleTo, degrees);
                System.out.printf("%s%s = %s%s", degrees, scaleFrom, resultDegrees, scaleTo);
            }
        } else {
            System.out.println("wrong input data");
        }
    }
}
