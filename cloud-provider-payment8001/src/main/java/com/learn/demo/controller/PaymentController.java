package com.learn.demo.controller;

import com.learn.demo.entities.CommonResult;
import com.learn.demo.entities.Payment;
import com.learn.demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(String.format("===============service:[%s]====================", service));
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-server");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    /**
     * 测试feign的请求超时接口
     * @return
     */
    @GetMapping("/payment/feigin/timeout")
    public CommonResult feignTimeout(){
        try {
            Thread.sleep(3000L);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new CommonResult(0, "请求成功，端口号为："+serverPort);
    }

    @GetMapping("/payment/hystrix/ok/{id}")
    public CommonResult paymentOK(@PathVariable("id") Integer id){
        String result = paymentService.paymentOK(id);
        log.info(result);
        return new CommonResult(0,result);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public CommonResult paymentTimeOut(@PathVariable("id") Integer id){
        String result = paymentService.paymentTimeOut(id);
        log.info(result);
        return new CommonResult(0,result);
    }

    @GetMapping("/payment/hystrix/circuit/{id}")
    public CommonResult paymentCircuit(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info(result);
        return new CommonResult(0,result);
    }
}
