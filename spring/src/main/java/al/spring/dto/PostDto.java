package al.spring.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostDto implements Serializable {
    private final Integer id;
    private final String title;
    private final String body;
}