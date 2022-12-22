# Web client

Используется для загрузки данных по API (в web flux - НЕ блокирующий подход)

1. Конфигурация
```java
import al.spring.repository.PostRepository;
import al.spring.service.JsonPlaceholderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AppConfig {
    
    /**
     * Загрузит посты при старте приложения.
     * CommandLineRunner - просто функциональный интерфейс
     */
    @Bean
    public CommandLineRunner getCommandLineRunner(
            PostRepository postRepository,
            @Value(value = "${posts.api.url}") String baseUrl
    ) {
        return args -> {
            var webClient = WebClient.builder().baseUrl(baseUrl);
            HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(
                    WebClientAdapter.forClient(webClient.build())
            ).build();

            var postService = factory.createClient(JsonPlaceholderService.class);
            var posts = postService.loadPosts();

            postRepository.saveAll(posts);
        };
    }
}
```

Просто определяем интерфейс для API:
```java
import al.spring.model.Post;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface JsonPlaceholderService {
    
    @GetExchange("/posts")
    List<Post> loadPosts();
}
```

Значение `@Value(value = "${posts.api.url}")` берется из конфига

[application.yml](..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fresources%2Fapplication.yml)

```yaml
posts:
  api:
    url: "https://jsonplaceholder.typicode.com/posts"
```
