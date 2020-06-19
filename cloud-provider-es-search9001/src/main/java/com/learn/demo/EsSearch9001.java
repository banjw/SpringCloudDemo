package com.learn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EsSearch9001 {

    public static void main(String[] args) {
        SpringApplication.run(EsSearch9001.class, args);
    }

}
