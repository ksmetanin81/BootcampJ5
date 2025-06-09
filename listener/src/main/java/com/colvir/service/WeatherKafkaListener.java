package com.colvir.service;

import com.colvir.dto.WeatherDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.colvir.config.KafkaConfig.TOPIC_NAME;

@Slf4j
@Component
public class WeatherKafkaListener {

    @KafkaListener(topics = TOPIC_NAME, concurrency = "2")
    public void onMessage(@Payload WeatherDto weatherDto) {
        log.info("Received message from Kafka: {}", weatherDto);
    }

}
