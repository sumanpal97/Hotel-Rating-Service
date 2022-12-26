package com.ms.user.service.configurations;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    @LoadBalanced   //used so that we can call a MS with their service name instead of host & port.Also balances the load
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
