package com.yubin.springcloud.eurekaconsumer.controller;

import com.yubin.springcloud.eurekaconsumer.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * RestTemplate案例控制层
 *
 * @author YUBIN
 * @create 2020-09-18
 */
@RestController
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * get请求处理 getForObject
     * @return
     */
    @RequestMapping("/testTemplate1")
    public String testTemplate1() {

        // 这个url中的 provider 指的是服务的名称, /test表示的是具体的接口地址
        String url = "http://provider/test";

        /**
         * 使用RestTemplate发起服务调用
         * 参数1：url
         * 参数2：反射的结果类型
         */
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }

    /**
     * get请求处理 返回一个Map
     * @return
     */
    @RequestMapping("/testTemplate2")
    public Object testTemplate2() {
        // 这个url中的 provider 指的是服务的名称, /getMap表示的是具体的接口地址
        String url = "http://provider/getMap";
        /**
         * 使用RestTemplate发起服务调用
         * 参数1：url
         * 参数2：反射的结果类型
         */
        ResponseEntity<Map> entity = restTemplate.getForEntity(url, Map.class);
        System.out.println(entity);
        return entity.getBody();
    }

    /**
     * get请求处理 返回一个对象
     * @return
     */
    @RequestMapping("/testTemplate3")
    public Object testTemplate3() {
        String url = "http://provider/getObj";
        /**
         * 使用RestTemplate发起服务调用
         * 参数1：url
         * 参数2：反射的结果类型
         */
        Person person = restTemplate.getForObject(url, Person.class);
        return person;
    }

    /**
     * get请求处理 使用占位符传递参数1
     * @return
     */
    @RequestMapping("/testTemplate4")
    public Object testTemplate4() {
        String url = "http://provider/getObjParam?name={1}";
        Person person = restTemplate.getForObject(url, Person.class,"haha");
        return person;
    }

    /**
     * get请求处理 使用map传递参数2
     * @return
     */
    @RequestMapping("/testTemplate5")
    public Object testTemplate5() {
        String url = "http://provider/getObjParam?name={name}";
        Map<String, Object> param = new HashMap<>();
        param.put("name", "xiaomi");
        Person person = restTemplate.getForObject(url, Person.class, param);
        return person;
    }

    /**
     * post请求处理
     * @return
     */
    @RequestMapping("/testTemplate6")
    public Object testTemplate6() {
        String url = "http://provider/postObjParam";
        Map<String, Object> param = new HashMap<>();
        param.put("name", "xiaomi");
        Person person = restTemplate.postForObject(url, param, Person.class);
        return person;
    }

    /**
     * post请求处理 postForLocation()
     * @return
     */
    @RequestMapping("/testTemplate7")
    public Object testTemplate7(HttpServletResponse response) throws IOException {
        String url = "http://provider/postForLocation";
        Map<String, Object> param = new HashMap<>();
        param.put("name", "xiaomi");
        URI uri = restTemplate.postForLocation(url, param, Person.class);
        // 重定向
        response.sendRedirect(uri.toURL().toString());
        return null;
    }
}
