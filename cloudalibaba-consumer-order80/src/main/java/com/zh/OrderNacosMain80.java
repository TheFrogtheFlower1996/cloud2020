package com.zh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zh
 * @version 1.0
 * @date 2022/3/4 17:03
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderNacosMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain80.class,args);
    }
}