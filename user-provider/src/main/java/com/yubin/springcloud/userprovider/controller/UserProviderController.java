package com.yubin.springcloud.userprovider.controller;

import com.yubin.springcloud.userapi.service.UserApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用户相关服务提供者控制层
 *
 * @author YUBIN
 * @create 2020-09-19
 */
@RestController
public class UserProviderController implements UserApi {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Value("${server.port}")
    private Integer port;

    @Override
    public String isActive() {
        //try {
        //    Thread.sleep(1000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //int i = 1 / 0;
        int count = atomicInteger.getAndIncrement();
        System.out.println("port:" + port + "第" + count + "次调用");
        return "user provider is ok:" + port;
    }

    @Override
    public Integer getById(Integer id) {
        System.out.println(id);
        return id;
    }

    @GetMapping("/getMap")
    public Map<Integer, String> getMap(Integer id) {
        System.out.println(id);
        return Collections.singletonMap(id, "么么哒");
    }

    @GetMapping("/getMap2")
    public Map<Integer, String> getMap2(Integer id, String name) {
        System.out.println(id);
        return Collections.singletonMap(id, name);
    }

    @GetMapping("/getMap3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }

    @PostMapping("/postMap")
    public Map<Integer, String> postMap(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }
}