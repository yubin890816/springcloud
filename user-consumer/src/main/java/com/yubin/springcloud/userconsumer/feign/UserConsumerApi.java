package com.yubin.springcloud.userconsumer.feign;

import com.yubin.springcloud.userapi.service.UserApi;
import com.yubin.springcloud.userconsumer.hystrix.UserProviderFallBackError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 用户服务调用API层
 *
 * @author YUBIN
 * @create 2020-09-19
 */
// name:表示服务提供者的服务名
//@FeignClient(name = "user-provider", fallback = UserProviderFallBack.class)
@FeignClient(name = "user-provider", fallbackFactory = UserProviderFallBackError.class)
public interface UserConsumerApi extends UserApi {

    @GetMapping("/getMap")
    Map<Integer, String> getMap(@RequestParam("id") Integer id);

    @GetMapping("/getMap2")
    Map<Integer, String> getMap2(@RequestParam("id") Integer id,@RequestParam("name") String name);

    @GetMapping("/getMap3")
    Map<Integer, String> getMap3(@RequestParam Map<String, Object> map);

    @PostMapping("/postMap")
    Map<Integer, String> postMap(Map<String, Object> map);

}
