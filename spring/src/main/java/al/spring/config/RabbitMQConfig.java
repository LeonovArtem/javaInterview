package al.spring.config;

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
