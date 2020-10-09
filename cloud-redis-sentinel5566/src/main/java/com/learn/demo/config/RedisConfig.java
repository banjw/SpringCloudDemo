package com.learn.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author banjiawei
 * @date 2020/10/08
 */
@Configuration
@Slf4j
public class RedisConfig {

    @Value("${myredis.sentinel.host}")
    private String redisSentinelHost;
    @Value("${myredis.sentinel.port}")
    private int redisSentinelPort;
    /**
     * @author banjiawei
     * @date 2020/10/8
     * @param
     * @return org.springframework.data.redis.connection.RedisConnectionFactory
     */
    @Bean
    public RedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig, RedisSentinelConfiguration sentinelConfig) {
        log.info("host={},port={}", redisSentinelHost, redisSentinelPort);
        return new JedisConnectionFactory(sentinelConfig, jedisPoolConfig);
    }

    /**
     * @author banjiawei
     * @date 2020/10/8
     * @param
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.String>
     */
    @Bean
    public RedisTemplate<String, String> getRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    public RedisSentinelConfiguration sentinelConfiguration(){
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        //配置matser的名称
        redisSentinelConfiguration.master("mymaster");
        //配置redis的哨兵sentinel
        Set<RedisNode> redisNodeSet = new HashSet<>();
        redisNodeSet.add(new RedisNode(redisSentinelHost, redisSentinelPort));
        log.info("redisNodeSet -->"+redisNodeSet);
        //哨兵模式添加set
        redisSentinelConfiguration.setSentinels(redisNodeSet);
        return redisSentinelConfiguration;
    }
}
