package com.aleonov.springkafka.simpleMessage;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class SimpleMessageConfig {
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                .partitions(2)
                .replicas(1)
                .build();
    }
}
