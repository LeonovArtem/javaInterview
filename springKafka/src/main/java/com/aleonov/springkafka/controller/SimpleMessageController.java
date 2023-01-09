package com.aleonov.springkafka.controller;

import com.aleonov.springkafka.simpleMessage.SimpleMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple-message")
@RequiredArgsConstructor
public class SimpleMessageController {
    private final SimpleMessageProducer simpleMessageProducer;

    @GetMapping
    public void sendToTopic1(String message) {
        simpleMessageProducer.send(message);
    }
}
