package al.spring.controller;

import al.spring.dto.UserDto;
import al.spring.mapper.UserMapper;
import al.spring.model.User;
import al.spring.service.UserService;
import al.spring.service.userNotification.UserNotifyService;
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
    private final UserNotifyService userNotifyService;

    @GetMapping
    public List<UserDto> list() {
        return userService.list().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("mapper/{id}")
    public UserDto findDtoById(@PathVariable int id) {
        return userMapper.toDto(userService.findById(id));
    }

    @GetMapping("{id}")
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @GetMapping("send-message")
    public void sendNotification(String message){
        userNotifyService.notify(message);
    }
}
