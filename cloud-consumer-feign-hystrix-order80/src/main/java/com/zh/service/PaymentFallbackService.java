package com.zh.service;

import org.springframework.stereotype.Component;

/**
 * @author zh
 * @version 1.0
 * @date 2022/2/27 19:48
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "PaymentFallbackService paymentInfo_ok Fallback 服务降级";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "PaymentFallbackService paymentInfo_timeout Fallback 服务降级";
    }
}
