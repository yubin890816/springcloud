package com.yubin.springcloud.userconsumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户消费者端控制层
 *
 * @author YUBIN
 * @create 2020-09-19
 */
@RestController
@RefreshScope
public class UserConsumerController {

    @Value("${config.info}")
    private String info;

    @GetMapping("/testConfig")
    public String testConfig() {

        return info;
    }
}
