package al.spring.config;

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

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

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
