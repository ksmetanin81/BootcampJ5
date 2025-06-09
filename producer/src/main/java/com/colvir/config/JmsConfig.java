package com.colvir.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class JmsConfig {

    public static final String DESTINATION_QUEUE = "queue.weather";
    public static final String DESTINATION_TOPIC = "topic.weather";
}
