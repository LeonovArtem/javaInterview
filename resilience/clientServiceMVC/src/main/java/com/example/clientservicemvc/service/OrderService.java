package com.example.clientservicemvc.service;

import com.example.clientservicemvc.dto.OrderDto;
import com.example.clientservicemvc.service.feign.OrderClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Value(value = "${app.orders.base-url}")
    private String orderBaseUrl;

    @Value(value = "${app.orders.get-list}")
    private String apiList;

    private final RestTemplate restTemplate;
    private final OrderClient orderClient;


    @CircuitBreaker(name = "jiraFeignClient", fallbackMethod = "getOrdersMock")
    public List<?> getOrdersFromRestTemplate() {
        return restTemplate
                .getForObject(orderBaseUrl + apiList, ArrayList.class);
    }

    public List<OrderDto> getOrdersFromFeignClient() {
        return orderClient.getList();
    }

    public List<?> getOrdersMock(Throwable e) {
        return List.of(
                new OrderDto(0, "it is mock_1"),
                new OrderDto(0, "it is mock_2")
        );
    }
}
