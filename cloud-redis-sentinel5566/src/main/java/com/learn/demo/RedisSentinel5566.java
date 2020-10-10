package com.learn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author banjiawei
 * @description
 * @date 2020/10/08
 */
@SpringBootApplication
@EnableEurekaClient
public class RedisSentinel5566 {
    public static void main(String[] args) {
        SpringApplication.run(RedisSentinel5566.class, args);
    }
}
