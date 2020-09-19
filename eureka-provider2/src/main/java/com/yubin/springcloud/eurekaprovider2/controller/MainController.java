package com.yubin.springcloud.eurekaprovider2.controller;

import com.yubin.springcloud.eurekaprovider2.bean.Person;
import com.yubin.springcloud.eurekaprovider2.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/getMap")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "哈哈");
        map.put("port", port);
        return map;
    }

    @GetMapping("/getObj")
    public Person getObj() {
        Person person = new Person();
        person.setId(100);
        person.setName("张三");
        return person;
    }

    @GetMapping("/getObjParam")
    public Person getObj(String name) {
        Person person = new Person();
        person.setId(100);
        person.setName(name);
        return person;
    }

    @PostMapping("/postObjParam")
    public Person postObjParam(@RequestBody Map<String, Object> map) {
        Person person = new Person();
        person.setId(102);
        person.setName(map.get("name") == null ? "" : map.get("name").toString());
        return person;
    }

    @PostMapping("/postForLocation")
    public URI postForLocation(@RequestBody Person person, HttpServletResponse response) throws URISyntaxException {
        URI uri = new URI("https://www.baidu.com/s?wd=" + person.getName());
        // 这个响应头一定得加
        response.addHeader("Location",uri.toString());
        return uri;
    }
}
