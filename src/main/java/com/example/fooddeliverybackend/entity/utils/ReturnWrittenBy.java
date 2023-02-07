package com.example.fooddeliverybackend.entity.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing

public class ReturnWrittenBy {
    @Bean
    AuditorAware<Long> auditorAware(){
        return new CreatedByUser();
    }
}
