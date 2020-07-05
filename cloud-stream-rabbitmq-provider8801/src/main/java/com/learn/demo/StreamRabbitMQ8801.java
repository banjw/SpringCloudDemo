package com.learn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitMQ8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitMQ8801.class, args);
    }
}
