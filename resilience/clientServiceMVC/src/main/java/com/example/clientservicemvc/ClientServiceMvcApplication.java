package com.example.clientservicemvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ClientServiceMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceMvcApplication.class, args);
    }

}
