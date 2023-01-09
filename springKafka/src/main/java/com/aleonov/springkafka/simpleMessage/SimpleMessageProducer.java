package com.aleonov.springkafka.simpleMessage;

import com.aleonov.springkafka.payload.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleMessageProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(UserDto userDto) {
        log.error("SimpleMessageProducer: Message send! message: {}", userDto);
        Message<UserDto> message = MessageBuilder
                .withPayload(userDto)
                .setHeader(KafkaHeaders.TOPIC, "topic1")
                .build();

        kafkaTemplate.send(message);
    }
}
