package com.yubin.springcloud.eurekaconsumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 消费者测试类
 *
 * @author YUBIN
 * @create 2020-09-17
 */
@RestController
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private EurekaClient client;

    @Autowired
    private LoadBalancerClient lbClient;

    @RequestMapping("/test1")
    public List<String> test1() {
        List<String> services = discoveryClient.getServices();
        return services;
    }

    @RequestMapping("/test2")
    public List<ServiceInstance> test2() {
        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        return provider;
    }

    @RequestMapping("/test3")
    public List<InstanceInfo> test3() {
        //List<InstanceInfo> instances = client.getInstancesById("yubin:provider:8081");
        List<InstanceInfo> instances = client.getInstancesByVipAddress("provider", false);
        if (instances.size() > 0) {
            InstanceInfo instance = instances.get(0);
            if (instance.getStatus() == InstanceInfo.InstanceStatus.UP) {
                String url = "http://" + instance.getHostName() + ":" + instance.getPort() + "/test";
                System.out.println(url);
                // 使用RestTemplate发起服务调用
                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(url, String.class);
                System.out.println(result);
            }
        }
        return instances;
    }

    /**
     * 使用负载均衡的方式调用远程服务
     * @return
     */
    @RequestMapping("/test4")
    public ServiceInstance test4() {
        // ribbon 完成客户端的负载均衡
        ServiceInstance instance = lbClient.choose("provider");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/test";
        System.out.println(url);
        // 使用RestTemplate发起服务调用
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        return instance;
    }
}
