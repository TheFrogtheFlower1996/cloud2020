package com.zh.service.controller;

import com.zh.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zh
 * @version 1.0
 * @date 2022/3/3 16:30
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping("/sendMessage")
    public String send(){
        return messageProvider.send();
    }
}
