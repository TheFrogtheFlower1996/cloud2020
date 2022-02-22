package com.zh.controller;

import com.zh.entities.CommonResult;
import com.zh.entities.Payment;
import com.zh.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zh
 * @version 1.0
 * @date 2022/1/24 22:36
 */
@RestController
@Slf4j //日志
public class PaymentController {

    // @Autowired 依赖注入
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果："+result);

        if (result > 0){
            return new CommonResult(200,"插入成功",result);
        }else {
            return new CommonResult(404,"插入失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果："+payment+"嘿嘿");

        if (payment != null){
            return new CommonResult(200,"查询成功，serverPort："+serverPort,payment);
        }else {
            return new CommonResult(404,"查询失败",null);
        }
    }

}
