package com.example.clientservicemvc.service.feign;

import com.example.clientservicemvc.configuration.CommonFeignClientConfiguration;
import com.example.clientservicemvc.dto.OrderDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "orderClient",
        url = "${app.orders.base-url}",
        configuration = CommonFeignClientConfiguration.class
)
@CircuitBreaker(name = "jiraFeignClient")
public interface OrderClient {

    String RETRY_CONFIG_LIVE_EVENT_INFO = "live_event_info";

    @GetMapping(
            value = "${app.orders.get-list}"
    )
    @Retry(name = RETRY_CONFIG_LIVE_EVENT_INFO)
    List<OrderDto> getList();
}
