package com.aleonov.reactivespring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
}
