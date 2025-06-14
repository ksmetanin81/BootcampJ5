package com.colvir.service;

import com.colvir.dto.WeatherDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.colvir.config.JmsConfig.DESTINATION_QUEUE;
import static com.colvir.config.JmsConfig.DESTINATION_TOPIC;


@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherJmsListener {

    private final ObjectMapper objectMapper;

    @JmsListener(destination = DESTINATION_QUEUE)
    public void onMessage(TextMessage message) throws JMSException, JsonProcessingException {
        WeatherDto weatherDto = objectMapper.readValue(message.getText(), WeatherDto.class);
        log.info("Received message from ActiveMQ: {}", weatherDto);
    }

    @JmsListener(destination = DESTINATION_TOPIC, containerFactory = "topicFactory")
    public void onMessageTopic(TextMessage message) throws JMSException, JsonProcessingException {
        WeatherDto weatherDto = objectMapper.readValue(message.getText(), WeatherDto.class);
        log.info("Received message from topic ActiveMQ: {}", weatherDto);
    }
}
