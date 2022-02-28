package com.zh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


/**
 * @author zh
 * @version 1.0
 * @date 2022/2/22 22:21
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("server.port")
    private String serverPort;

    @RequestMapping(value = "payment/consul")
    public String paymentConsul(){

        return "springCloud consul: "+serverPort + UUID.randomUUID().toString();
    }

}
