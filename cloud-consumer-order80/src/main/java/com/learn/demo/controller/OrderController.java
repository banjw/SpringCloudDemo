package com.learn.demo.controller;

import com.learn.demo.entities.CommonResult;
import com.learn.demo.entities.Payment;
import com.learn.demo.lb.LoadBalancerDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
//    public static final String PAYMENT_URL = "http://localhost:8001";
    //使用服务名称得和LoadBalanced配合使用，RestTemplate得加该注解，要不然就要写具体的IP加Port
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVER";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancerDemo loadBalancerDemo;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/add")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/add", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
    //使用restTemplate.getForEntity
    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getPaymentEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> getForEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(getForEntity.getStatusCode().is2xxSuccessful()){
            return getForEntity.getBody();
        }else {
            return new CommonResult<>(getForEntity.getStatusCode().value(),"请求失败！");
        }
    }
    //使用restTemplate.postForEntity
    @GetMapping("/consumer/payment/addEntity")
    public CommonResult<Payment> createForEntity(Payment payment) {
        ResponseEntity<CommonResult> postForEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment/add", payment, CommonResult.class);
        if(postForEntity.getStatusCode().is2xxSuccessful()){
            return postForEntity.getBody();
        }else {
            return new CommonResult<>(postForEntity.getStatusCode().value(), "请求失败");
        }
    }

    //调用自己写的轮询负载均衡实现类
    @GetMapping("/consumer/payment/loadbalance/get/{id}")
    public CommonResult<Payment> getLoadBalanceDemoPayment(@PathVariable("id") Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVER");
        if(null == loadBalancerDemo || instances.size() == 0){
            return new CommonResult<>(-1, "请求失败");
        }else {
            ServiceInstance instance = loadBalancerDemo.getInstance(instances);
            URI uri = instance.getUri();
            //依赖注入的使用默认的负载规则，现在是替换为随机的访问规则，而new出来的会使用自己写的轮询负载规则访问
            return new RestTemplate().getForObject(uri+"/payment/get/" + id, CommonResult.class);
        }
    }
}
