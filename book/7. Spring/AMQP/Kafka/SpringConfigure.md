# Spring Config
Docker
```yaml
version: '3'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      # Периодичность с которой будет опрашивать Кафку - на предмет того не упала ли она
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - "2181:2181"

  kafka:
    image: confluentinc/cp-kafka:latest
    # После какого сервиса запускать Кафку
    depends_on:
      - zookeeper
    ports:
      - "19093:19093"
    environment:
      # Количество инстансов кафки
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: 'zookeeper:2181'
      KAFKA_LISTENERS: DOCKER_NETWORK://0.0.0.0:9092,CONNECTIONS_FROM_HOST://0.0.0.0:19093
      KAFKA_ADVERTISED_LISTENERS: DOCKER_NETWORK://kafka:9092,CONNECTIONS_FROM_HOST://localhost:19093
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: DOCKER_NETWORK:PLAINTEXT,CONNECTIONS_FROM_HOST:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: DOCKER_NETWORK
      # Кол-во нод
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1

  # https://github.com/obsidiandynamics/kafdrop - UI
  kafdrop:
    image: obsidiandynamics/kafdrop
    restart: "no"
    ports:
      - "9000:9000"
    environment:
      KAFKA_BROKERCONNECT: kafka:9092
      JVM_OPTS: "-Xms16M -Xmx48M -Xss180K -XX:-TieredCompilation -XX:+UseStringDeduplication -noverify"
    depends_on:
      - kafka
```
[kafka.yml](..%2F..%2F..%2F..%2FspringKafka%2Fsrc%2Fmain%2Fresources%2Fkafka.yml)
```yaml
spring:
  kafka:
    bootstrap-servers: ${APP_KAFKA_CONSUMER_BOOTSTRAP_SERVERS:localhost:19093}
```
### 1. Simple message
```java
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class SimpleMessageConfig {
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }
}
```
Отправка сообщений
```java
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
```
Consumer
```java
import org.springframework.kafka.annotation.KafkaListener;

public class SimpleMessageConsumer {

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(String message) {
        System.out.println(message);
    }
}
```