package com.yubin.springcloud.userconsumer.controller;

import com.yubin.springcloud.userconsumer.feign.UserConsumerApi;
import com.yubin.springcloud.userconsumer.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户消费者端控制层
 *
 * @author YUBIN
 * @create 2020-09-19
 */
@RestController
@RequestMapping("/user/consumer")
public class UserConsumerController {

    @Autowired
    private UserConsumerApi userConsumerApi;

    @RequestMapping("/providerIsActive")
    public String providerIsActive() {
        return userConsumerApi.isActive();
    }

    @Autowired
    private RestService restService;

    @RequestMapping("/isActive2")
    public String isActive2() {
        return restService.isActive();
    }

    /**
     * try {
     *      -------限流处理-------
     *      map(URI,线程数)
     *      线程池(线程数)
     *      if(当前线程满了){
     *          throw new exception();
     *      }
     *      1、向服务端发起请求
     *          1.1、判断连接超时
     *              -> 这次请求记录到服务里
     *          1.2、尝试向其它服务发起请求
     *      2、还是没成功
     *      -------熔断-------
     *      计数连续失败次数,达到阈值 (count++)
     *      if(count==10){
     *          throw new exception();
     *      }
     *      恢复：开/关/半开(时不时的试一试)
     * } catch(Exception e){
     *      -------降级处理-------
     *      1、降级方式1：避免返回不友好的信息->跳转友好页面
     *      2、降级方式2：发送到MQ里面去
     *      ......
     *     return "客观稍后再来";
     * }
     */

    @RequestMapping("/findById")
    public Integer findById() {
        return userConsumerApi.getById(12);
    }

    @GetMapping("/map")
    public Map<Integer, String> map(Integer id) {
        System.out.println(id);
        return userConsumerApi.getMap(id);
    }

    @GetMapping("/map2")
    public Map<Integer, String> map2(Integer id, String name) {
        System.out.println(id);
        return userConsumerApi.getMap2(id,name);
    }


    @GetMapping("/map3")
    public Map<Integer, String> map3(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return userConsumerApi.getMap3(map);
    }


    @GetMapping("/map4")
    public Map<Integer, String> map4(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return userConsumerApi.postMap(map);
    }
}
