### RabbitMQ

### 1. Подключение

[pom.xml](..%2F..%2F..%2Fspring%2Fpom.xml)
```xml
        <!-- Rabbit -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.amqp</groupId>
            <artifactId>spring-rabbit-test</artifactId>
            <scope>test</scope>
        </dependency>
```
[application.yml](..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fresources%2Fapplication.yml)
```yaml
spring:
  rabbitmq:
    password: ${RABBITMQ_PASSWORD:guest}
    host: ${RABBITMQ_HOST:localhost}
    port: ${RABBITMQ_PORT:5672}
    username: ${RABBITMQ_USERNAME:guest}
    listener:
      simple:
        retry:
          initial-interval: '1000'
          enabled: 'true'
          multiplier: '2.0'
          max-attempts: '3'
    virtual-host: ${RABBITMQ_VHOST:/}
```
[RabbitMQConfig.java](..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fjava%2Fal%2Fspring%2Fconfig%2FRabbitMQConfig.java)
```java
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJackson2MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public TopicExchange topicExchange(@Value(value = "${amqp.rabbit.queue.exchange.topic}") String exchangeName) {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public DirectExchange directExchange(@Value(value = "${amqp.rabbit.queue.exchange.direct}") String exchangeName) {
        return new DirectExchange(exchangeName);
    }

    @Bean(name = "rabbitMessageConvertor")
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            @Qualifier("rabbitMessageConvertor") AbstractJackson2MessageConverter converter
    ) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }
}

```

### 2. Настраиваем очереди

[RabbitQueuesConfig.java](..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fjava%2Fal%2Fspring%2Fconfig%2FRabbitQueuesConfig.java)
```java
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueuesConfig {
    @Bean(name = "queue-demo-direct-1")
    public Queue demoDirectQueue(
            @Value(value = "${amqp.rabbit.queue.user_like_queue.queue_name}") String queueName
    ) {
        return new Queue(queueName, false);
    }

    @Bean
    public Binding bindingDemoDirect(
            @Qualifier("queue-demo-direct-1") Queue queue,
            DirectExchange exchange,
            @Value(value = "${amqp.rabbit.queue.user_like_queue.routing_key}") String routingKey
    ) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
}
```
[application.yml](..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fresources%2Fapplication.yml)
```yaml
# RabbitMQ: Queues
amqp:
  rabbit:
    queue:
      exchange:
        topic: demo_topic_exchange
        direct: demo_direct_exchange
      user_like_queue:
        cnt_consumers: 1
        queue_name: user_like_queue
        routing_key: user_like_routing_key
```
Consumer:
[UserLikeConsumer.java](..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fjava%2Fal%2Fspring%2Famqp%2FUserLikeConsumer.java)

```java
import al.spring.dto.UserLikeDto;
import al.spring.service.UserLike;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLikeConsumer {
    private final UserLike userLike;

    @RabbitListener(
            queues = "${amqp.rabbit.queue.user_like_queue.queue_name}",
            concurrency = "${amqp.rabbit.queue.user_like_queue.cnt_consumers}"
    )
    public void execute(UserLikeDto userLikeDto){
        // Обработка в очереди...
        userLike.increaseLikeForUser(userLikeDto.getUserId());
    }
}
```
Publisher

[UserLikePublisher.java](..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fjava%2Fal%2Fspring%2Famqp%2FUserLikePublisher.java)

```java
import al.spring.dto.UserLikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLikePublisher {
    @Value(value = "${amqp.rabbit.queue.exchange.direct}")
    private String exchangeName = "demo_direct_exchange";

    @Value(value = "${amqp.rabbit.queue.user_like_queue.routing_key}")
    private String routingKey = "user_like_routing_key";

    private final RabbitTemplate rabbitTemplate;

    public void publish(Integer userId) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, new UserLikeDto(userId));
    }
}

```
Dto:
```java
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserLikeDto implements Serializable {
    private Integer userId;
}
```

### Поверяем работу:
Проверяем (Apache benchmark):
```shell
# 1000 запросов, 50 потоков
ab -n 1000 -c 50 localhost:5006/like/rabbit?userId=1
```

