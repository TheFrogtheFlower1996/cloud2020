package com.zh.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zh.entities.CommonResult;
import com.zh.entities.Payment;
import com.zh.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zh
 * @version 1.0
 * @date 2022/3/22 22:58
 * @description:说明
 */
@RestController
public class RateLimitController {

    // 按资源名
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "byResourceHandler")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名限流测试",new Payment(2020L,"serial001"));
    }

    public CommonResult byResourceHandler(BlockException e){
        return new CommonResult(444,e.getClass().getCanonicalName()+"\t 服务不可用");
        //com.alibaba.csp.sentinel.slots.block.flow.FlowException 限流异常类
    }

    // 按url
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按URL限流测试",new Payment(2020L,"serial002"));
    }

    // 配置 全局统一降级类
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "/customerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"按 全局统一降级类 限流测试");
    }

}
