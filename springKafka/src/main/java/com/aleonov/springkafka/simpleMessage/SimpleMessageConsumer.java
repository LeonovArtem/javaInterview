package com.aleonov.springkafka.simpleMessage;

import com.aleonov.springkafka.payload.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimpleMessageConsumer {

    @KafkaListener(id = "myId", topics = "topic1", concurrency = "4")
    public void listen(UserDto userDto) {
        log.warn("SimpleMessageConsumer: {}", userDto);
    }
}
