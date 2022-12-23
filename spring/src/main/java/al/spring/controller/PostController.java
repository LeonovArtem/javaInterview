package al.spring.controller;

import al.spring.model.Post;
import al.spring.service.PostLoaderService;
import al.spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {
    private final PostLoaderService postLoaderService;
    private final PostService postService;

    @GetMapping
    public List<Post> getAll() {
        return postService.list();
    }

    @GetMapping("{id}")
    public Post findById(@PathVariable int id) {
        return postService.getById(id);
    }

    @PostMapping
    public void create(Post post) {
        postService.create(post);
    }

    @DeleteMapping
    public void delete(int id) {
        postService.deleteById(id);
    }

    @PostMapping("load")
    public List<Post> loadPosts() {
        var posts = postLoaderService.loadPosts();

        return posts;
    }
}
