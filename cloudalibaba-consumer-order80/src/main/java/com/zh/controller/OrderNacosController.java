package com.zh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zh
 * @version 1.0
 * @date 2022/3/4 17:14
 */
@RestController
@Slf4j
public class OrderNacosController {

    @Autowired
    public RestTemplate restTemplate;

    @Value("${server-url.nacos-user-service}")
    public String serverURL;

    @GetMapping("/order/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id){
       return restTemplate.getForObject(serverURL+"/payment/nacos/"+id,String.class);
    }
}
