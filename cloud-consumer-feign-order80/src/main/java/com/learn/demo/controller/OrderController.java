package com.learn.demo.controller;

import com.learn.demo.entities.CommonResult;
import com.learn.demo.entities.Payment;
import com.learn.demo.service.FeignPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    @GetMapping("/consumer/feign/payment/hystrix/ok/{id}")
    public CommonResult paymentOK(@PathVariable("id") Integer id){
        return feignPaymentService.paymentOK(id);
    }

    @GetMapping("/consumer/feign/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public CommonResult paymentTimeOut(@PathVariable("id") Integer id){
        return feignPaymentService.paymentTimeOut(id);
    }

    public CommonResult paymentTimeOutHandler(@PathVariable("id") Integer id){
        return new CommonResult(-1, "线程名： "+Thread.currentThread().getName()+", 这边是80消费端，对方支付系统繁忙请稍后再试，或者本身系统出错！");
    }
}
