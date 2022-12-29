package al.spring.controller;

import al.spring.BaseFunctionalTest;
import al.spring.model.Post;
import al.spring.service.PostLoaderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends BaseFunctionalTest {
    @MockBean
    private PostLoaderService postLoaderService;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void loadPosts() throws Exception {
        List<Post> posts = List.of(new Post(), new Post());
        Mockito.doReturn(posts).when(postLoaderService).loadPosts();

        mockMvc
                .perform(post("/post/load").contentType("application/json"))
                .andExpect(status().isOk());
    }
}