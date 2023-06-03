package com.example.clientservicemvc.cron;

import com.example.clientservicemvc.repository.UserRepository;
import com.example.clientservicemvc.service.OrdersByUserCollector;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatisticCollector {

    private final OrdersByUserCollector ordersByUserCollector;
    private final UserRepository userRepository;

    @Timed(value = "process_timer", extraTags = {"scheduled", "synchronizing_employees_all"})
//    @Scheduled(fixedDelayString = "P1D", initialDelayString = "PT1M")
    public void process() {
        log.info("Starting synchronizing users");
        ordersByUserCollector.collect(
                userRepository.getUsers(30)
        );
        log.info("Finishing synchronizing...");
    }
}
