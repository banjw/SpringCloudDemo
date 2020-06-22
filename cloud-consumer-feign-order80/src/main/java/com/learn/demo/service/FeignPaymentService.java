package com.learn.demo.service;

import com.learn.demo.entities.CommonResult;
import com.learn.demo.entities.Payment;
import com.learn.demo.service.impl.FeignPaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVER", fallback = FeignPaymentFallbackService.class)//没有特别指定的，都走全局处理方法
public interface FeignPaymentService {
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/feigin/timeout")
    CommonResult feignTimeout();

    @GetMapping("/payment/hystrix/ok/{id}")
    CommonResult paymentOK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    CommonResult paymentTimeOut(@PathVariable("id") Integer id);
}
