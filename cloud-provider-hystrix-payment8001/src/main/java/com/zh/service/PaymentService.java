package com.zh.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author zh
 * @version 1.0
 * @date 2022/2/25 15:28
 */
@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id) {
        return "服务端 线程池：" + Thread.currentThread().getName() + "paymentInfo_ok，id:" + id + "\t" + "完成";
    }

    //------------------   服务降级
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")})
    public String paymentInfo_timeout(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int i = 10/0;
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_timeout，id:" + id + "\t" + "请求超时，出错啦";
    }

    public String paymentInfo_timeoutHandler(Integer id) {
        return "服务端 线程池：" + Thread.currentThread().getName() + " paymentInfo_timeoutHandler，id: " + id + "\t" + "请求超时，系统繁忙或运行错误，请稍后再试。";
    }

    // ------------------  服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"), //开启熔断器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "1000"), //时间窗口 10秒
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "5"), //失败率达到多少熔断跳闸
    })
    public String paymentCircuitBreaker(Long id) {
        System.out.println("id="+id);

//        int i =10/0;

        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String s = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + " 调用成功，id= " + s;
    }

    public String paymentCircuitBreaker_fallback(Long id) {
        return " 熔断降级方法 paymentCircuitBreaker_fallback id= " + id;
    }

}
