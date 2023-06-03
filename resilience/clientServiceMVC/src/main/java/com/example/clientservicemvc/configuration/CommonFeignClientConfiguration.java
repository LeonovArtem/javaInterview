package com.example.clientservicemvc.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;

public class CommonFeignClientConfiguration {

    @Bean
    public Decoder decoder() {
        return new JacksonDecoder(
                JsonMapper.builder()
                        .addModule(new JavaTimeModule())
                        .addModules(new ParameterNamesModule())
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
                        .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                        .build()
        );
    }

    @Bean
    public Encoder encoder() {
        return new JacksonEncoder();
    }

    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }
}
