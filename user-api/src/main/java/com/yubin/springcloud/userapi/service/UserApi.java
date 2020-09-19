package com.yubin.springcloud.userapi.service;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户操作相关服务api接口
 *
 * @author YUBIN
 * @create 2020-09-19
 */
@RequestMapping("/user")
public interface UserApi {

    @RequestMapping("/isActive")
    public String isActive();
}
