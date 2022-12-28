package al.spring.amqp;

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
