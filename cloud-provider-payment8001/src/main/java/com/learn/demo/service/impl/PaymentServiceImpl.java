package com.learn.demo.service.impl;

import com.learn.demo.dao.PaymentDao;
import com.learn.demo.entities.Payment;
import com.learn.demo.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DefaultProperties(defaultFallback = "paymentGloblaFallbackHandler")
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    @HystrixCommand//只加了注解，没有指定的，使用全局fallback方法
    public String paymentOK(Integer id) {
        //人为制造异常或
//        int a =  10/0;
        return "线程池:\t"+Thread.currentThread().getName()+"paymentOK:\t"+id+"==================================";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String paymentTimeOut(Integer id) {
        long startTime = System.currentTimeMillis();
        try {
            //人为制造异常或超时都会走到fallback方法
//            int a = 10 / 0;
            Thread.sleep(3*1000L);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池:\t"+Thread.currentThread().getName()+"paymentTimeOut:\t"+id+"==================================耗时（ms）"+(System.currentTimeMillis() - startTime);
    }

    @Override
    public String paymentTimeOutHandler(Integer id) {
        return "线程池:  "+Thread.currentThread().getName()+"paymentTimeOutHandler:  "+id+"==================================";
    }

    public String paymentGloblaFallbackHandler(){
        return "线程池:  "+Thread.currentThread().getName()+"全局降级处理";
    }
}
