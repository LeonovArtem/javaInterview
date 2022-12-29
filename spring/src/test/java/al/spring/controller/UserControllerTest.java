package al.spring.controller;

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
