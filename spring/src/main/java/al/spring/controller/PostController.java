package al.spring.controller;

import al.spring.exception.PostNotFoundException;
import al.spring.model.Post;
import al.spring.repository.PostRepository;
import al.spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @GetMapping("{id}")
    public Post findById(@PathVariable int id) {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException(id))
                ;
    }

    @PostMapping
    public void create() {
        var post = (new Post())
                .setUserId(1)
                .setTitle("first")
                .setBody("it is body...");
        postRepository.save(post);
    }

    @PostMapping("load")
    public List<Post> showLoadPosts() {
        var posts = postService.loadPosts();

        return posts;
    }
}
