package com.aleonov.springkafka.simpleMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple-message")
@RequiredArgsConstructor
public class SimpleMessageController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping
    public void sendToTopic1(){
        kafkaTemplate.send("topic1", "hello");
    }
}
