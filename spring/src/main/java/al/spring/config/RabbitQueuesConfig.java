package al.spring.config;

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
