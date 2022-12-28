# Транзакции

1. https://habr.com/ru/company/rosbank/blog/707378/ - практическая часть
2. https://habr.com/ru/post/347752/ - Spring AOP. Маленький вопросик с собеседования
3. https://habr.com/ru/company/otus/blog/574470/ - Spring @Transactional — ошибки, которые совершали все
4. https://habr.com/ru/company/otus/blog/649093/ - Лучший способ использовать аннотацию Spring Transactional

### Проблема: нужно изменить поле в нескольких потоках
```java
@RestController
@RequestMapping("like")
@RequiredArgsConstructor
public class UserLikeController {
    private final UserLike userLike;

    @GetMapping
    public void like(int userId) {
        userLike.increaseLikeByTransaction(userId);
    }
}
```
Решение:
1. Используем @Retryable(maxAttempts = 3) - по умолчанию!
Ставим на 15 так как чем больше потоков (выше конкурентность тем чаще будут Deadlock)
```
o.h.engine.jdbc.spi.SqlExceptionHelper   : Deadlock found when trying to get lock; try restarting transaction
```

```java
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
```
[pom.xml](..%2F..%2F..%2Fspring%2Fpom.xml)
```xml
        <dependency>
            <groupId>org.springframework.retry</groupId>
            <artifactId>spring-retry</artifactId>
            <version>2.0.0</version>
        </dependency>
```
Application.class
```java
@SpringBootApplication
// Включаем Retry
@EnableRetry
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```
Проверяем (Apache benchmark):
```shell
# 1000 запросов, 50 потоков
ab -n 1000 -c 50 localhost:5006/like?userId=1
```
```sql
# Должно быть 1000
select count_likes
from user
where id = 1;
```