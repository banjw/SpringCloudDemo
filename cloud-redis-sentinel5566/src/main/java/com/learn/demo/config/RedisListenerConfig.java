package com.learn.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author banjiawei
 * @date 2020/10/10
 * redis过期监听配置
 */
@Configuration
@Slf4j
public class RedisListenerConfig {
    @Autowired
    @Qualifier("jedisConnectionFactory0")
    private RedisConnectionFactory redisConnectionFactory0;

    @Autowired
    @Qualifier("jedisConnectionFactory15")
    private RedisConnectionFactory redisConnectionFactory15;

    @Bean(name = "redisMessageListenerContainer0")
    RedisMessageListenerContainer redisMessageListenerContainer0() {
        log.info("===================RedisListenerConfig:redisMessageListenerContainer0");
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory0);
        return redisMessageListenerContainer;
    }
    @Bean(name = "redisMessageListenerContainer15")
    RedisMessageListenerContainer redisMessageListenerContainer15() {
        log.info("=====================RedisListenerConfig:redisMessageListenerContainer15");
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory15);
        return redisMessageListenerContainer;
    }
}
