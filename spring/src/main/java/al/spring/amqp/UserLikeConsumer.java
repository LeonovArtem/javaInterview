package al.spring.amqp;

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
        userLike.increaseLikeForUser(userLikeDto.getUserId());
    }
}
