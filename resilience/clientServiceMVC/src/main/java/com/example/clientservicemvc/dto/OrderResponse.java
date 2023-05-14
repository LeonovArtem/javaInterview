package com.example.clientservicemvc.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private List<OrderDto> orders;
}
