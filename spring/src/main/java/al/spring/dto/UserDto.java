package al.spring.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {
    private final Integer id;
    private final String name;
    private final List<PostDto> posts;
    private final List<UserRoleDto> roles;
}