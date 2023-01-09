package com.aleonov.springkafka.simpleMessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimpleMessageConsumer {

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(String message) {
        log.warn("SimpleMessageConsumer: {}", message);
        System.out.println(message);
    }
}
