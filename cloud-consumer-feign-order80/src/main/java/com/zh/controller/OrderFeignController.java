package com.zh.controller;

import com.zh.entities.CommonResult;
import com.zh.entities.Payment;
import com.zh.service.PaymentFeignService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zh
 * @version 1.0
 * @date 2022/2/25 10:02
 */
@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){

        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openFeign-ribbon 客户端访问接口 等待时间默认为1秒
        return paymentFeignService.paymentFeignTimeout();

    }


}
