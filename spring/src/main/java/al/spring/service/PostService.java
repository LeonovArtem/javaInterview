package al.spring.service;

import al.spring.model.Post;

import java.util.List;

public interface PostService {

    void create(Post post);

    List<Post> list();

    Post getById(Integer id);

    void deleteById(Integer id);
}
