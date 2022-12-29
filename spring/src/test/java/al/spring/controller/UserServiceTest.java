package al.spring.controller;

import al.spring.BaseFunctionalTest;
import al.spring.model.User;
import al.spring.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserServiceTest extends BaseFunctionalTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void createUser(){
        var user = new User();
        user.setName("test_name");
        userRepository.save(user);
    }

    @AfterEach
    void deleteAllUsers(){
        userRepository.deleteAll();
    }

    @Test
    void getUserByIdTest(){
        var user = userRepository.findById(1);

        System.out.println(user);
    }
}
