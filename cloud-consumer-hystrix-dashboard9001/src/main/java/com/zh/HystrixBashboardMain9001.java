package com.zh;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

/**
 * @author zh
 * @version 1.0
 * @date 2022/2/28 11:01
 */
@SpringBootApplication
@EnableHystrixDashboard //开启 hystrix Dashboard 可视化工具
public class HystrixBashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixBashboardMain9001.class,args);
    }
}
