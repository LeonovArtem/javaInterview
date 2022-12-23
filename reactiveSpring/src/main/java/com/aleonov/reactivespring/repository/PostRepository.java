package com.aleonov.reactivespring.repository;

import com.aleonov.reactivespring.entity.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PostRepository extends ReactiveCrudRepository<Post, Integer> {
}
