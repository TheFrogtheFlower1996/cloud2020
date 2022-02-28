package com.zh.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author zh
 * @version 1.0
 * @date 2022/2/25 11:28
 * Feign 日志增强配置类
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){

        return Logger.Level.FULL;
    }
}
