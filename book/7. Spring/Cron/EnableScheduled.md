### Cron

[SchedulingConfiguration.java](..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fjava%2Fal%2Fspring%2Fconfig%2FSchedulingConfiguration.java)
```java
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Profile("!test")
@Configuration
@EnableScheduling
@ConditionalOnProperty(
        value = "app.scheduling.enable", havingValue = "true", matchIfMissing = true
)
public class SchedulingConfiguration {
    /*_*/
}
```
Cron example:
[StatisticInfo.java](..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fjava%2Fal%2Fspring%2Fcron%2FStatisticInfo.java)
```java
@Slf4j
@Component
@RequiredArgsConstructor
public class StatisticInfo {
    private final UserRepository userRepository;
    
    // Выполняется каждую минут. Запускать через 2 минуты после старта приложения
    @Scheduled(fixedDelayString = "PT1M", initialDelayString = "PT2M")
    public void execute(){
        userRepository.findAll()
                .forEach(user -> log.info("[userId: {}] User count likes: {}", user.getId(), user.getCountLikes()));
    }
}
```