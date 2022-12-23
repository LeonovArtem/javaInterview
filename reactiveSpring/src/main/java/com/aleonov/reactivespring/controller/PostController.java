package com.aleonov.reactivespring.controller;

import com.aleonov.reactivespring.entity.Post;
import com.aleonov.reactivespring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;

    @GetMapping
    public Flux<Post> list(){
        return postRepository.findAll();
    }
}
