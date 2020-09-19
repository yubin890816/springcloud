package com.yubin.springcloud.eurekaconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 负载均衡的客户端控制层
 *
 * @author YUBIN
 * @create 2020-09-18
 */
@RestController
public class LoadBalanceConsumerController {

    @Autowired
    private LoadBalancerClient lbClient;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 客户端负载均衡演示
     * @return
     */
    @RequestMapping("/testLoadBalance")
    public String testLoadBalance() {
        // ribbon 完成客户端的负载均衡
        ServiceInstance instance = lbClient.choose("provider");

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/test";

        // 使用RestTemplate发起服务调用
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 自定义负载负载均衡算法-随机
     * @return
     */
    @RequestMapping("/testLoadBalance2")
    public String testLoadBalance2() {

        List<ServiceInstance> instances = discoveryClient.getInstances("provider");
        int index = new Random().nextInt(instances.size());
        ServiceInstance instance = instances.get(index);

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/test";

        // 使用RestTemplate发起服务调用
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * 自定义负载负载均衡算法-轮询
     * @return
     */
    @RequestMapping("/testLoadBalance3")
    public String testLoadBalance3() {

        List<ServiceInstance> instances = discoveryClient.getInstances("provider");
        int index = atomicInteger.getAndIncrement();
        index = index % instances.size();
        ServiceInstance instance = instances.get(index);

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/test";

        // 使用RestTemplate发起服务调用
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }

    /**
     * 自动处理URL
     * @return
     */
    @RequestMapping("/testLoadBalance4")
    public String testLoadBalance4() {

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
}
