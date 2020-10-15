package com.learn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Redis 集群
 *
 * @author banjiawei
 * @date 2020/10/15
 */
@SpringBootApplication
@EnableEurekaClient
public class RedisCluster6677 {
    public static void main(String[] args) {
        SpringApplication.run(RedisCluster6677.class, args);
    }
}
