package com.zh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zh
 * @version 1.0
 * @date 2022/3/4 16:37
 */
@SpringBootApplication
@EnableDiscoveryClient //开启服务发现客户端
public class PaymentMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9002.class,args);
    }
}
