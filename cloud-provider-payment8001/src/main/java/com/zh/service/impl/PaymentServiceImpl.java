package com.zh.service.impl;

import com.zh.dao.PaymentDao;
import com.zh.entities.Payment;
import com.zh.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zh
 * @version 1.0
 * @date 2022/1/24 22:27
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }

}
