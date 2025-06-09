package com.colvir.scheduler;

import com.colvir.dto.WeatherDto;
import com.colvir.service.WeatherJmsProducer;
import com.colvir.service.WeatherKafkaProducer;
import com.colvir.service.WeatherReader;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.colvir.config.KafkaConfig.TOPIC_NAME;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final WeatherReader weatherReader;
    private final WeatherJmsProducer jmsProducer;
    private final WeatherKafkaProducer kafkaProducer;

    @Scheduled(fixedDelay = 10_000)
    public void run() throws IOException, ParseException {
        WeatherDto weatherDto = weatherReader.read();
        jmsProducer.send(weatherDto);
        jmsProducer.sendTopic(weatherDto);
        kafkaProducer.send(TOPIC_NAME, weatherDto);
    }
}
