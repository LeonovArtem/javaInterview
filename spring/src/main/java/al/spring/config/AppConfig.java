package al.spring.config;

import al.spring.repository.PostRepository;
import al.spring.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
