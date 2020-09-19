package com.yubin.springcloud.userconsumer.controller;

import com.yubin.springcloud.userconsumer.feign.UserConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @RequestMapping("/findById")
    public Integer findById() {
        return userConsumerApi.getById(12);
    }

    @GetMapping("/map")
    public Map<Integer, String> map(Integer id) {
        System.out.println(id);
        return userConsumerApi.getMap(id);
    }

    @GetMapping("/map2")
    public Map<Integer, String> map2(Integer id, String name) {
        System.out.println(id);
        return userConsumerApi.getMap2(id,name);
    }


    @GetMapping("/map3")
    public Map<Integer, String> map3(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return userConsumerApi.getMap3(map);
    }


    @GetMapping("/map4")
    public Map<Integer, String> map4(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return userConsumerApi.postMap(map);
    }
}
