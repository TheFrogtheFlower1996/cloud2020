package com.zh.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import com.zh.entities.CommonResult;
import com.zh.entities.Payment;
import com.zh.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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

    @Resource
    private EurekaDiscoveryClient eurekaDiscoveryClient;

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

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){

        List<String> services = eurekaDiscoveryClient.getServices();
        for (String service : services) {
            log.info("**** service:"+service);//service:cloud-consumer-order; service:cloud-consumer-order
        }
        List<ServiceInstance> instances = eurekaDiscoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getUri()+"\t"+instance.getPort());
            //payment8002	172.16.66.166	http://172.16.66.166:8002	8002
            //payment8001	172.16.66.166	http://172.16.66.166:8001	8001
        }
        return  this.eurekaDiscoveryClient;
    }

}
