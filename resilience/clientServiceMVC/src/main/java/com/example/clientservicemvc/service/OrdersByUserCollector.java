package com.example.clientservicemvc.service;

import com.example.clientservicemvc.dto.UserDto;
import com.example.clientservicemvc.service.feign.OrderClient;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersByUserCollector {

    private final OrderClient orderClient;

    public void collect(List<UserDto> users) {
        users.stream()
                .forEach(this::doProcess);
    }

    @Timed(value = "process_timer", extraTags = {"scheduled", "order_for_user"})
    private void doProcess(UserDto userDto) {
        log.warn("Call for userId {}", userDto.id());
        try {
            var response = orderClient.getList();
            log.info("Response for userId {} response {}", userDto.id(), response);
        } catch (Exception e){
            log.error("orderClient error for userId: {}", userDto.id());
        }
    }
}
