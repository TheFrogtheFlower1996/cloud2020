package com.zh.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zh
 * @version 1.0
 * @date 2022/3/16 22:23
 */
@RestController
@Log4j2
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){

        return "testA";
    }

    @GetMapping("/testB")
    public String testB(){

        return "testB";
    }

    @GetMapping("/testC")
    public String testC(){

        // 睡眠3秒
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testC");
        return "testC";
    }

    @GetMapping("/testD")
    public String testD(){
        log.info("testD 异常比例测试");
        int i = 10/0;
        return "testD";
    }

    @GetMapping("/testE")
    public String testE(){
        log.info("testE 异常数测试");
        int i = 10/0;
        return "testE";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){

        return "HotKey 热点限流 "+ p1 + p2;
    }
    public String deal_testHotKey(String p1, String p2, BlockException e){
        return "热点限流 hotkey 降级兜底方法";
    }
}
