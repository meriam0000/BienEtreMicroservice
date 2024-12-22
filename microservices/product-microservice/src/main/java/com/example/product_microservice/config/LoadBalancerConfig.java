package com.example.product_microservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LoadBalancerConfig {
    @Bean
    @LoadBalanced // Active l'équilibrage de charge avec Spring Cloud LoadBalancer
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
