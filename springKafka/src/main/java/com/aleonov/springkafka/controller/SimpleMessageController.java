package com.aleonov.springkafka.controller;

import com.aleonov.springkafka.payload.UserDto;
import com.aleonov.springkafka.simpleMessage.SimpleMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/simple-message")
@RequiredArgsConstructor
public class SimpleMessageController {
    private final SimpleMessageProducer simpleMessageProducer;

    @GetMapping
    public void sendToTopic1(String message) {
        var user = new UserDto();
        var generator = new Random();
        user.setId(generator.nextInt());
        user.setName(message);

        simpleMessageProducer.send(user);
    }
}
