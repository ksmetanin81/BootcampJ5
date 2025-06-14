package com.colvir.service;

import com.colvir.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherKafkaProducer {

    private final KafkaTemplate<String, WeatherDto> kafkaTemplate;
    private final AtomicInteger keyCounter = new AtomicInteger(0);

    public void send(String topic, WeatherDto weatherDTO) {
        CompletableFuture<SendResult<String, WeatherDto>> future = kafkaTemplate.send(topic, keyCounter.incrementAndGet() + "", weatherDTO);
        future.whenComplete((result, exception) -> {
            if (exception != null) {
                log.error("Error while sending message! Check Kafka broker", exception);
            } else {
                log.info("Sent message successfully to Kafka {} with offset {}", weatherDTO, result.getRecordMetadata().offset());
            }
        });
    }
}
