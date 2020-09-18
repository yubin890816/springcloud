package com.yubin.springcloud.eurekaprovider1.controller;

import com.yubin.springcloud.eurekaprovider1.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供服务的controller
 *
 * @author YUBIN
 * @create 2020-09-16
 */
@RestController
public class MainController {

    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/test")
    public String test() {
        return "hello provider,我的port:" + port;
    }

    @Autowired
    private HealthStatusService healthStatusService;

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus().toString();
    }
}
