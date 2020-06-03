package com.learn.demo.service;

import com.learn.demo.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(@Param("id") Integer id);

    //演示Hystrix的接口
    String paymentOK(Integer id);
    String paymentTimeOut(Integer id);
    //fallback method
    String paymentTimeOutHandler(Integer id);
}
