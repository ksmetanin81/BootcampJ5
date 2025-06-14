package com.colvir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProducerRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProducerRunner.class, args);
    }
}