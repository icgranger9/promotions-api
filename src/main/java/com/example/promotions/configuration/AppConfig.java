package com.example.promotions.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.promotions.service.impl.PromotionServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public PromotionServiceImpl promotionService() {
        return new PromotionServiceImpl();
    }
}
