package com.learn.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * Redisson 配置类
 *
 * @author banjiawei
 * @date 2020/10/15
 */
@Configuration
public class RedissonConfig {
    @Value("#{'${spring.redis.cluster.nodes}'.split(',')}")
    private Set<String> nodes;

    private static final String REDIS_PROTOCOL = "redis://";
    @Bean
    public Config config(){
        Config config = new Config();
        nodes.forEach(clusterNode -> {
            config.useClusterServers().addNodeAddress(REDIS_PROTOCOL + clusterNode);
        });
        config.useClusterServers();
        //.setPassword("password");
        return config;
    }
    @Bean
    public RedissonClient redissonClient(Config config){
        return Redisson.create(config);
    }
}
