package al.spring.controller;

import al.spring.dto.UserDto;
import al.spring.mapper.UserMapper;
import al.spring.model.User;
import al.spring.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<User> list() {
        return userService.list();
    }

    @Transactional
    @GetMapping("mapper/{id}")
    public UserDto findDtoById(@PathVariable int id) {
        return userMapper.toDto(userService.findById(id));
    }

    @Transactional
    @GetMapping("{id}")
    public User findById(@PathVariable int id) {
        var user = userService.findById(id);
        System.out.println(user.getPosts());

        return user;
    }
}
