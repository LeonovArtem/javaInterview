package al.spring.controller;

import al.spring.model.Post;
import al.spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;

    @GetMapping
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @PostMapping
    public void create() {
        var post = new Post();
        postRepository.save(post);
    }
}
