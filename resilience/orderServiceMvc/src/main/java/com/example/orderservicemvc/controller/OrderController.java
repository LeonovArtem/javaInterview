package com.example.orderservicemvc.controller;

import com.example.orderservicemvc.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @GetMapping
    public List<Order> list() {
        return List.of(
                new Order(1, "first order"),
                new Order(2, "second order")
        );
    }
}
