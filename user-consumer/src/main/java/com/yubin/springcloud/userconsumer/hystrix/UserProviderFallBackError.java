package com.yubin.springcloud.userconsumer.hystrix;

import com.yubin.springcloud.userconsumer.feign.UserConsumerApi;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 能获取服务接口异常的降级实现类
 *
 * @author YUBIN
 * @create 2020-09-20
 */
@Component
public class UserProviderFallBackError implements FallbackFactory<UserConsumerApi> {
    @Override
    public UserConsumerApi create(Throwable cause) {
        return new UserConsumerApi() {
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
                System.out.println(cause);
                if (cause instanceof FeignException.InternalServerError) {
                    return "远程服务异常:" + cause;
                }
                if (cause instanceof RuntimeException) {
                    return "请求时异常:" + cause;
                }
                return "UserProviderFallBackError isActive 服务降级了, 不确定的异常";
            }

            @Override
            public Integer getById(Integer id) {
                return null;
            }
        };
    }
}
