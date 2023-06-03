package com.example.clientservicemvc.controller;

import com.example.clientservicemvc.dto.OrderDto;
import com.example.clientservicemvc.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("rest")
    public List<?> getByRestClient() {
        return orderService.getOrdersFromRestTemplate();
    }

    @GetMapping("feign")
    public List<OrderDto> getByFeignClient() {
        return orderService.getOrdersFromFeignClient();
    }
}
