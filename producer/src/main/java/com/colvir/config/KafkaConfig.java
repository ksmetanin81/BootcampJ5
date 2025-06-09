package com.colvir.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
public class KafkaConfig {

    public static final String TOPIC_NAME = "topic.weather";

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC_NAME).partitions(2).build();
    }
}
