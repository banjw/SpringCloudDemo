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

    @Autowired
    @Qualifier("redisTemplate0")
    private  RedisTemplate<String, Object> redisTemplate0;
    @Autowired
    @Qualifier("redisTemplate15")
    private  RedisTemplate<String, Object> redisTemplate15;

    @Test
    public void getString(){
        Object v1 = redisTemplate0.opsForValue().get("k1");
        log.info("k1={}", v1);
        Object test15 = redisTemplate15.opsForValue().get("test15");
        log.info("test15={}", test15);
    }

}
