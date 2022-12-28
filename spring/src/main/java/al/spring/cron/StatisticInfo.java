package al.spring.cron;

import al.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatisticInfo {
    private final UserRepository userRepository;

    @Scheduled(fixedDelayString = "PT1M")
    public void execute(){
        userRepository.findAll()
                .forEach(user -> log.info("[userId: {}] User count likes: {}", user.getId(), user.getCountLikes()));
    }
}
