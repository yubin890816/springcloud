package com.yubin.springcloud.userconsumer.hystrix;

import com.yubin.springcloud.userconsumer.feign.UserConsumerApi;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用户服务相关降级类
 *
 * @author YUBIN
 * @create 2020-09-20
 */
@Component
public class UserProviderFallBack implements UserConsumerApi {
    @Override
    public Map<Integer, String> getMap(Integer id) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap2(Integer id, String name) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap3(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<Integer, String> postMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public String isActive() {

        return "isActive 方法降级了";
    }

    @Override
    public Integer getById(Integer id) {
        return null;
    }
}
