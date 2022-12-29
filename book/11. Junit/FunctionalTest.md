## Functional test
* [mockMvc - запросы к MVC контроллерам](#1-MockMwc)
* [@MockBean - мокаем сервис](#2-mockbean)

### 1. MockMwc

Не забыть включить `@AutoConfigureMockMvc` в [BaseFunctionalTest.java](..%2F..%2Fspring%2Fsrc%2Ftest%2Fjava%2Fal%2Fspring%2FBaseFunctionalTest.java)

```java
import al.spring.BaseFunctionalTest;
import al.spring.model.User;
import al.spring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseFunctionalTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findById_whenUserExist_thenReturn200() throws Exception {
        var user = createUserForTestById();

        String url = String.format("/user/%d", user.getId());
        mockMvc
                .perform(get(url).contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void findById_whenUserNotExist_thenReturn404() throws Exception {
        String url = String.format("/user/%d", 1);
        mockMvc
                .perform(get(url).contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    private User createUserForTestById() {
        var user = new User();
        user.setName("test");
        userRepository.save(user);

        return user;
    }
}
```
### 2. @MockBean

```java
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
```
