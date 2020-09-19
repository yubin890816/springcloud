package com.yubin.springcloud.userconsumer.feign;

import com.yubin.springcloud.userapi.service.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户服务调用API层
 *
 * @author YUBIN
 * @create 2020-09-19
 */
// name:表示服务提供者的服务名
@FeignClient(name = "user-provider")
public interface UserConsumerApi extends UserApi {

}
