package al.spring.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRequestDto {
    private final String name;
    private final List<PostDto> posts;
    private final List<UserRoleDto> roles;
}
