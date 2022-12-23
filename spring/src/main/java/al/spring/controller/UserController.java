package al.spring.controller;

import al.spring.model.User;
import al.spring.service.UserService;
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

    @GetMapping
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }
}
