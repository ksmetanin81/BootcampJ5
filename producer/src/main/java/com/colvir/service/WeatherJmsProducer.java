package com.colvir.service;

import com.colvir.dto.WeatherDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.colvir.config.JmsConfig.DESTINATION_QUEUE;
import static com.colvir.config.JmsConfig.DESTINATION_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherJmsProducer {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public void send(WeatherDto weatherDto) throws JsonProcessingException {
        jmsTemplate.convertAndSend(new ActiveMQQueue(DESTINATION_QUEUE), objectMapper.writeValueAsString(weatherDto), m ->
                {
                    log.info("Sent message to ActiveMQ: {}", weatherDto);
                    return m;
                }
        );
    }

    public void sendTopic(WeatherDto weatherDto) throws JsonProcessingException {
        jmsTemplate.convertAndSend(new ActiveMQTopic(DESTINATION_TOPIC), objectMapper.writeValueAsString(weatherDto), m ->
                {
                    log.info("Sent message to topic ActiveMQ: {}", weatherDto);
                    return m;
                }
        );

    }
}
