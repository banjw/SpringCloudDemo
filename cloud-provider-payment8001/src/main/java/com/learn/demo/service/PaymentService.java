package com.learn.demo.service;

import com.learn.demo.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(@Param("id") Integer id);

    //演示Hystrix服务降级的接口
    String paymentOK(Integer id);
    String paymentTimeOut(Integer id);
    //fallback method
    String paymentTimeOutHandler(Integer id);

    //服务熔断
    String paymentCircuitBreaker(Integer id);
    String paymentCircuitBreakerFallback(Integer id);
}
