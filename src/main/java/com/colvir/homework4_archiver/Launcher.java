package com.colvir.homework4_archiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(Launcher.class, args)) {
            Scanner console = new Scanner(System.in);

            System.out.println("Enter the path to the file");
            String filePath = console.nextLine();

            String zipFile = applicationContext.getBean(ArchiverService.class).archive(filePath);

            System.out.printf("complite: %s", zipFile);
        }
    }
}
