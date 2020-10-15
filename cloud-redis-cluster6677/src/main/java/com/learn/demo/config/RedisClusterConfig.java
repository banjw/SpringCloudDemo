package com.learn.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * RedisCluster配置类
 *
 * @author banjiawei
 * @date 2020/10/15
 */
@Configuration
public class RedisClusterConfig {
    @Value("${spring.redis.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${spring.redis.maxTotal}")
    private int maxTotal;
    @Value("${spring.redis.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.redis.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.redis.maxIdle}")
    private int maxIdle;
    @Value("#{'${spring.redis.cluster.nodes}'.split(',')}")
    private Set<String> nodes;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        return config;
    }

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration(){
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(nodes);
        //如果有设置密码的话
        //redisClusterConfiguration.setPassword("");
        return redisClusterConfiguration;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(RedisClusterConfiguration redisClusterConfiguration, JedisPoolConfig jedisPoolConfig){
        return new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        setRedisTemplateSerializer(redisTemplate);
        return redisTemplate;
    }

    /**
     * 设置序列化方式
     * @author banjiawei
     * @date 2020/10/10
     * @param redisTemplate
     * @return void
     */
    private void setRedisTemplateSerializer(RedisTemplate<String, Object> redisTemplate) {
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        // 设置Key的序列化方式
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        // 设置value的序列化方式
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.afterPropertiesSet();
    }
}
