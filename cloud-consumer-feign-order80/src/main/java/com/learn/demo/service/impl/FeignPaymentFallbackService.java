package com.learn.demo.service.impl;

import com.learn.demo.entities.CommonResult;
import com.learn.demo.entities.Payment;
import com.learn.demo.service.FeignPaymentService;
import org.springframework.stereotype.Component;

@Component
public class FeignPaymentFallbackService implements FeignPaymentService {
    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        return new CommonResult<>(1, "对方服务宕机，请稍后再试");
    }

    @Override
    public CommonResult feignTimeout() {
        return new CommonResult<>(1, "对方服务宕机，请稍后再试");
    }

    @Override
    public CommonResult paymentOK(Integer id) {
        return new CommonResult<>(1, "对方服务宕机，请稍后再试");
    }

    @Override
    public CommonResult paymentTimeOut(Integer id) {
        return new CommonResult<>(1, "对方服务宕机，请稍后再试");
    }
}
