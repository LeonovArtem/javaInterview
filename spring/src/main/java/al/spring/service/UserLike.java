package al.spring.service;

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

    @Retryable(maxAttempts = 15)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void increaseLikeByTransaction(Integer userId) {
        var user = userRepository.findById(userId).get();
        var countLikes = user.getCountLikes() + 1;
        user.setCountLikes(countLikes);
        userRepository.save(user);
        log.warn("Count like: {}", countLikes);
    }
}
