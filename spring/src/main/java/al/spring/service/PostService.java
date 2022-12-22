package al.spring.service;

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
                postApiUrl + "/posts",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Post>>() {
                }
        );

        return exchange.getBody();
    }

}
