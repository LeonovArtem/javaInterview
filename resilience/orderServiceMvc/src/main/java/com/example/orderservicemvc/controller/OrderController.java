package com.example.orderservicemvc.controller;

import com.example.orderservicemvc.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private Integer count = 0;

    @GetMapping
    public List<Order> list() {
        log.info("Number of request: {}", count++);

//        if (count >= 0) {
//            throw new Error();
//        }

        return List.of(
                new Order(1, "first order"),
                new Order(2, "second order")
        );
    }
}
