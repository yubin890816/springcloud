package com.yubin.springcloud.userconsumer.controller;

import com.yubin.springcloud.userconsumer.feign.UserConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户消费者端控制层
 *
 * @author YUBIN
 * @create 2020-09-19
 */
@RestController
@RequestMapping("/user/consumer")
public class UserConsumerController {

    @Autowired
    private UserConsumerApi userConsumerApi;

    @RequestMapping("/providerIsActive")
    public String providerIsActive() {
        return userConsumerApi.isActive();
    }
}
