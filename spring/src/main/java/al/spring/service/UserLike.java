package al.spring.service;

import al.spring.amqp.UserLikePublisher;
import al.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserLike {
    private final UserRepository userRepository;
    private final UserLikePublisher userLikePublisher;

    private long start;

    @Retryable(maxAttempts = 15)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void increaseLikeByTransaction(Integer userId) {
        increaseLikeForUser(userId);
    }

    public void publish(Integer userId) {
        userLikePublisher.publish(userId);
    }

    public void increaseLikeForUser(Integer userId) {
        var user = userRepository.findById(userId).get();
        var countLikes = user.getCountLikes() + 1;

        if (countLikes == 1) {
            start = System.currentTimeMillis();

        }

        user.setCountLikes(countLikes);
        userRepository.save(user);

        if (countLikes == 2000) {
            var end = System.currentTimeMillis();
            System.out.println("Consumers execution time: " + (end -start) + " ms");
        }
    }
}
