package al.spring.service;

import al.spring.exception.PostNotFoundException;
import al.spring.model.Post;
import al.spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public void create(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> list() {
        return postRepository.findAll();
    }

    @Override
    public Post getById(Integer id) {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @Override
    public void deleteById(Integer id) {
        postRepository.deleteById(id);
    }
}
