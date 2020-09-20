package com.yubin.springcloud.userapi.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户操作相关服务api接口
 *
 * @author YUBIN
 * @create 2020-09-19
 */
//@RequestMapping("/user")
public interface UserApi {

    @GetMapping("/isActive")
    public String isActive();

    /**
     * 这里的GetMapping是给Feign看的,给Feign拼接url用的
     * 这里的@RequestParam也是给Feign看的,给Feign组装参数用的
     * 注意：如果不加@RequestParam的话，参数无法传递给服务端
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public Integer getById(@RequestParam Integer id);
}