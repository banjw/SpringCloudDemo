package com.learn.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author banjiawei
 * @description
 * @date 2020/10/08
 */
@SpringBootTest
@Slf4j
public class RedisSentinelTest5566 {
    @Qualifier("getRedisTemplate")
    @Autowired
    private  RedisTemplate<String, String> redisTemplate;

    @Test
    public void getString(){
        String v1 = redisTemplate.opsForValue().get("k1");
        log.info("k1={}", v1);
//        redisTemplate.opsForValue().set("test", "test");
//        Object test = redisTemplate.opsForValue().get("test");
//        log.info("test={}", test);
    }
}
