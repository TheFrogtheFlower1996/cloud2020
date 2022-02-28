package com.zh.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zh.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zh
 * @version 1.0
 * @date 2022/2/26 21:34
 */
@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") //默认Fallback服务降级方法
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){

        String s = paymentHystrixService.paymentInfo_ok(id);
        return s;
    }

//    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000")})
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        int i = 9/0;
        String s = paymentHystrixService.paymentInfo_timeout(id);
        return s;
    }
    public String paymentInfo_timeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_timeoutHandler，id: "+id+"\t"+" 我是消费者80，提供者请求超时，系统繁忙或运行错误";
    }

    public String payment_Global_FallbackMethod(){
        return "消费者80 全局Fallback 服务降级";
    }
}
