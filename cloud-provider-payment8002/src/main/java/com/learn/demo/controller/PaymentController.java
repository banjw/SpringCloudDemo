package com.learn.demo.controller;

import com.learn.demo.entities.CommonResult;
import com.learn.demo.entities.Payment;
import com.learn.demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Integer id){
        Payment payment = paymentService.getPaymentById(id);
        if(null != payment){
            log.info(String.format("请求成功,serverPort: [%s], payment: [%s]", serverPort, payment));
            return new CommonResult<>(0,"请求成功,serverPort: "+ serverPort, payment);
        }else {
            return new CommonResult<>(1, "请求失败");
        }
    }

    @PostMapping(value = "/payment/add")
    public CommonResult addPayment(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        if(i == 1){
            log.info(String.format("添加成功,serverPort: [%s], payment: [%s]", serverPort, payment));
            return new CommonResult(0, String.format("添加成功,serverPort: [%s]", serverPort) , payment);
        }else {
            return new CommonResult(1, "添加失败");
        }

    }
}
