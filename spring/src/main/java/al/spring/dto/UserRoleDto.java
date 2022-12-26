package al.spring.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleDto implements Serializable {
    private final String name;
}
