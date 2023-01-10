package com.aleonov.springkafka.controller;

import com.aleonov.springkafka.payload.UserDto;
import com.aleonov.springkafka.simpleMessage.SimpleMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("balancing-example")
    public void balancingExample(Integer count) {
        generateUser(count).forEach(simpleMessageProducer::send);
    }

    private List<UserDto> generateUser(Integer count) {
        List<UserDto> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            var user = new UserDto();
            user.setId(i);
            user.setName("user_" + i);

            list.add(user);
        }

        return list;
    }
}
