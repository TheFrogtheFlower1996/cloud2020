package com.zh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zh
 * @version 1.0
 * @date 2022/2/23 10:16
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于使用consul作为注册中心时注册服务
public class ConsulOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulOrderMain80.class,args);
    }
}
