# Rest Template

Используется для загрузки данных по API (в WEB MVC - блокирующий подход)

1. Конфигурация
```java
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * Загрузит посты при старте приложения.
     * CommandLineRunner - просто функциональный интерфейс
     */
    @Bean
    public CommandLineRunner getCommandLineRunner(PostRepository postRepository, PostService postService) {
        return args -> {
            var posts = postService.loadPosts();
            postRepository.saveAll(posts);
        };
    }
}
```
Пример:
```java
import al.spring.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final RestTemplate restTemplate;

    @Value(value = "${posts.api.url}")
    private String postApiUrl;

    public List<Post> loadPosts() {
        ResponseEntity<List<Post>> exchange = restTemplate.exchange(
                postApiUrl,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Post>>() {
                }
        );

        return exchange.getBody();
    }

}
```

Значение `@Value(value = "${posts.api.url}")` берется из конфига

[application.yml](..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fresources%2Fapplication.yml)

```yaml
posts:
  api:
    url: "https://jsonplaceholder.typicode.com/posts"
```

