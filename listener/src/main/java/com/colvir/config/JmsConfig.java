package com.colvir.config;

import jakarta.jms.ConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JmsConfig {

    public static final String DESTINATION_QUEUE = "queue.weather";
    public static final String DESTINATION_TOPIC = "topic.weather";
    public static final String JMS_TRANSACTION_MANAGER_NAME = "jmsTransactionManager";

    @Bean(JMS_TRANSACTION_MANAGER_NAME)
    PlatformTransactionManager jmsTransactionManager(ConnectionFactory connectionFactory) {
        return new JmsTransactionManager(connectionFactory);
    }

    @Bean
    public JmsListenerContainerFactory<?> topicFactory(
            ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer,
            PlatformTransactionManager jmsTransactionManager) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        factory.setTransactionManager(jmsTransactionManager);
        factory.setSessionTransacted(true);
        return factory;
    }
}
