package com.zh.service;

import com.zh.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author zh
 * @version 1.0
 * @date 2022/1/24 22:25
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
