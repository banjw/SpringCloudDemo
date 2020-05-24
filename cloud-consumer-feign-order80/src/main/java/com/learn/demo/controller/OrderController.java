package com.learn.demo.controller;

import com.learn.demo.entities.CommonResult;
import com.learn.demo.entities.Payment;
import com.learn.demo.service.FeignPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private FeignPaymentService feignPaymentService;

    @GetMapping("/consumer/feign/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return feignPaymentService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feigin/timeout")
    public CommonResult feignTimeout(){
        //openfeign-ribbon 默认超时等待1s
        return feignPaymentService.feignTimeout();
    }
}
