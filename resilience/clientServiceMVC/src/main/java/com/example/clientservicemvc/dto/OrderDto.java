package com.example.clientservicemvc.dto;

import java.io.Serializable;

public record OrderDto(
        Integer id,
        String title
) implements Serializable {

}
