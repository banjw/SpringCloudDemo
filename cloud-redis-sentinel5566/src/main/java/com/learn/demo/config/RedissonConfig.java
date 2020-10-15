package com.learn.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @author banjiawei
 * @date 2020/10/15
 * Redisson配置类
 */
@Configuration
@Slf4j
public class RedissonConfig {
    @Value("${spring.redis.maxTotal}")
    private int maxTotal;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.sentinel.master}")
    private String myMasterName;
    @Value("#{'${spring.redis.sentinel.nodes}'.split(',')}")
    private Set<String> nodes;

    private static final String REDIS_PROTOCOL = "redis://";

    @Bean
    public Config config3(){
        Config config = new Config();
        nodes.forEach(redisSentinelNode -> {
            config.useSentinelServers().addSentinelAddress(REDIS_PROTOCOL + redisSentinelNode);
        });
        config.useSentinelServers()
                .setMasterName(myMasterName)
                .setTimeout(timeout)
                .setDatabase(3)
                .setMasterConnectionPoolSize(maxTotal)
                .setSlaveConnectionPoolSize(maxTotal);
                //.setPassword("password");
        return config;
    }

    @Bean
    public RedissonClient redissonClient3(Config config){
        return Redisson.create(config);
    }
}
