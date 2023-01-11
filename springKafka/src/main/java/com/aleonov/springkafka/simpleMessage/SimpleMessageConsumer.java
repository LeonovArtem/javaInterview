package com.aleonov.springkafka.simpleMessage;

import com.aleonov.springkafka.payload.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimpleMessageConsumer {

    @KafkaListener(id = "myId", topics = "topic1", concurrency = "4")
    public void listen(UserDto userDto) {
        log.warn("SimpleMessageConsumer: {}", userDto);
    }

    @KafkaListener(id = "newMyId", topics = "topic1")
    public void consume(ConsumerRecord<String, UserDto> record) {
        log.warn("AnotherConsumer: value: {}, offset: {}", record.value(), record.offset());
    }
}
