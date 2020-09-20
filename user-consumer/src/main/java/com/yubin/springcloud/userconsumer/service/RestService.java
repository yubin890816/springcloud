package com.yubin.springcloud.userconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate请求封装服务类
 *
 * @author YUBIN
 * @create 2020-09-20
 */
@Service
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "back")
    public String isActive() {
        String url = "http://user-provider/isActive";
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    public String back() {
        return "restTemplate 结合 hystrix降级";
    }
}
