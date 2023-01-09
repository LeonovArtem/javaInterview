package com.aleonov.springkafka.simpleMessage;

import org.springframework.kafka.annotation.KafkaListener;

public class SimpleMessageConsumer {

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(String message) {
        System.out.println(message);
    }
}
