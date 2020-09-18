package com.yubin.springcloud.eurekaprovider2.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * 健康状态服务层
 *
 * @author YUBIN
 * @create 2020-09-17
 */
@Service
public class HealthStatusService implements HealthIndicator {

    private Boolean status = true;

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public Health health() {

        if (status) {
            return new Health.Builder().up().build();
        }
        return new Health.Builder().down().build();
    }

    public Boolean getStatus() {
        return status;
    }
}
