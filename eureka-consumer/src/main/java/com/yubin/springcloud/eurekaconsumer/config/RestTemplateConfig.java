package com.yubin.springcloud.eurekaconsumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate初始化配置
 *
 * @author YUBIN
 * @create 2020-09-18
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
