package al.spring.controller;

import al.spring.dto.UserDto;
import al.spring.dto.UserRequestDto;
import al.spring.mapper.UserMapper;
import al.spring.model.User;
import al.spring.service.UserFactory;
import al.spring.service.UserService;
import al.spring.service.userNotification.UserNotifyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Tag(name = "Пользователи")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserNotifyService userNotifyService;
    private final UserFactory userFactory;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Список пользователей")
    public List<UserDto> list() {
        return userService.list().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("mapper/{id}")
    public UserDto findDtoById(@PathVariable int id) {
        return userMapper.toDto(userService.findById(id));
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Найти пользователя по ID", responses = {
            @ApiResponse(responseCode = "200", description = "Пользователь", content = @Content(
                    schema = @Schema(implementation = User.class)
            )),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content(
                    schema = @Schema(implementation = User.class)
            )),
    })
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @GetMapping("send-message")
    public void sendNotification(String message) {
        userNotifyService.notify(message);
    }

    @PostMapping
    @Operation(description = "Создать пользователя")
    public User createUser(@RequestBody UserRequestDto userRequestDto) {
        return userFactory.registerAccount(userRequestDto);
    }
}
