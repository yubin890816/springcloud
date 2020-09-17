package com.yubin.springcloud.eurekaprovider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供服务的controller
 *
 * @author YUBIN
 * @create 2020-09-16
 */
@RestController
public class MainController {

    @RequestMapping("/test")
    public String test() {
        return "hello provider";
    }
}
