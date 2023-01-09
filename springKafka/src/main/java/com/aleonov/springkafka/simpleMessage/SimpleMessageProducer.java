package com.aleonov.springkafka.simpleMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleMessageProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {
        log.error("SimpleMessageProducer: Message send! message: {}", message);
        kafkaTemplate.send("topic1", message);
    }
}
