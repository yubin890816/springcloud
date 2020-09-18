package com.yubin.springcloud.eurekaconsumer.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 负载均衡规则配置类
 *
 * @author YUBIN
 * @create 2020-09-18
 */
@Configuration
public class LoadBalanceRuleConfig {

    //@Bean
    public IRule myRule() {
        //return new RoundRobinRule();
        //return new RandomRule();
        return new RandomRule(); // 随机策略
    }
}
