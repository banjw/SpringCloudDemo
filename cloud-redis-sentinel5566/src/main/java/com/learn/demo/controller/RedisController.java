package com.learn.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author banjiawei
 * @date 2020/10/10
 * redis测试controller
 */
@RestController
@Slf4j
public class RedisController {
    @Autowired
    @Qualifier("redisTemplate0")
    private RedisTemplate<String, Object> redisTemplate0;

    @Autowired
    @Qualifier("redisTemplate15")
    private  RedisTemplate<String, Object> redisTemplate15;

    @GetMapping("/0/{key}")
    public Object getDatabase0Value(@PathVariable("key") String key){
        Object value = redisTemplate0.opsForValue().get(key);
        log.info("value = {}", value);
        return value;
    }

    @GetMapping("/0/expire/{key}/{value}")
    public String setDatabase0KeyExpire(@PathVariable("key") String key, @PathVariable("value") String value){
        redisTemplate0.opsForValue().set(key, value);
        redisTemplate0.expire(key,1L, TimeUnit.MINUTES);
        log.info("key = {}, value = {}, 过期时间1分钟", key, value);
        return "OK";
    }

    @GetMapping("/15/{key}")
    public Object getDatabase15Value(@PathVariable("key") String key){
        Object value = redisTemplate15.opsForValue().get(key);
        log.info("value = {}", value);
        return value;
    }

    @GetMapping("/15/expire/{key}/{value}")
    public String setDatabase15KeyExpire(@PathVariable("key") String key, @PathVariable("value") String value){
        redisTemplate15.opsForValue().set(key, value);
        redisTemplate15.expire(key,1L, TimeUnit.MINUTES);
        log.info("key = {}, value = {}, 过期时间1分钟", key, value);
        return "OK";
    }
}
