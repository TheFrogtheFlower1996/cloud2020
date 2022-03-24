package com.zh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author zh
 * @version 1.0
 * @date 2022/2/28 11:01
 */
@SpringBootApplication
@EnableHystrixDashboard //开启 hystrix Dashboard 可视化工具
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class,args);
    }
}
