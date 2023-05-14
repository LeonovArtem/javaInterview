package com.example.clientservicemvc.controller;

import com.example.clientservicemvc.dto.OrderDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final RestTemplate restTemplate;
    private static final String ORDERS_BASE_URL = "http://localhost:9090/api/v1/orders";

    @GetMapping("rest")
    @CircuitBreaker(name = "circularService1", fallbackMethod = "getOrdersMock")
    public List<?> getByRestClient() {
        return restTemplate
                .getForObject(ORDERS_BASE_URL, ArrayList.class);
    }

    public List<?> getOrdersMock(Throwable e) {
        return List.of(
                new OrderDto(0, "it is mock_1"),
                new OrderDto(0, "it is mock_2")
        );
    }
}
