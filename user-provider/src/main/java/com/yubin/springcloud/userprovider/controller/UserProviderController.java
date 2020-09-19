package com.yubin.springcloud.userprovider.controller;

import com.yubin.springcloud.userapi.service.UserApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关服务提供者控制层
 *
 * @author YUBIN
 * @create 2020-09-19
 */
@RestController
public class UserProviderController implements UserApi {

    @Override
    public String isActive() {
        return "user provider is ok";
    }
}