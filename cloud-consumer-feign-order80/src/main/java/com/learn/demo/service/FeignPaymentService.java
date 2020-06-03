package com.learn.demo.service;

import com.learn.demo.entities.CommonResult;
import com.learn.demo.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVER")
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
