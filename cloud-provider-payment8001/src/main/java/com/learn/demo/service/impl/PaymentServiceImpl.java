package com.learn.demo.service.impl;

import com.learn.demo.dao.PaymentDao;
import com.learn.demo.entities.Payment;
import com.learn.demo.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

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
        return "线程名:\t"+Thread.currentThread().getName()+"paymentOK:\t"+id+"==================================";
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
        return "线程名:\t"+Thread.currentThread().getName()+"paymentTimeOut:\t"+id+"==================================耗时（ms）"+(System.currentTimeMillis() - startTime);
    }

    @Override
    public String paymentTimeOutHandler(Integer id) {
        return "线程名:  "+Thread.currentThread().getName()+"paymentTimeOutHandler:  "+id+"==================================";
    }

    public String paymentGloblaFallbackHandler(){
        return "线程名:  "+Thread.currentThread().getName()+"全局降级处理";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",
            commandProperties = {//参数需要参考com.netflix.hystrix.HystrixCommandProperties类中的默认配置
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率多少后跳闸
            }
    )
    public String paymentCircuitBreaker(Integer id) {
        if(id < 0){
            throw new RuntimeException("id不能为负数");
        }
        return "线程名:  "+Thread.currentThread().getName()+"paymentTimeOutHandler: "+id+"=================================="+UUID.randomUUID();
    }

    @Override
    public String paymentCircuitBreakerFallback(Integer id) {
        return  "线程名:  "+Thread.currentThread().getName()+ String.format(" id[%d]不能为负数，请稍后再试", id);
    }

}
