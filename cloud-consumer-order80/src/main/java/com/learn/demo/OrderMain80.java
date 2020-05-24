package com.learn.demo;

import com.learn.rule.RandomRuleDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//说明要访问的服务以及所使用的自定义负载均衡类
@RibbonClient(name = "CLOUD-PAYMENT-SERVER", configuration = RandomRuleDemo.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
