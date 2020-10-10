package com.learn.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author banjiawei
 * @date 2020/10/10
 * RedisSentinel配置类
 */
@Configuration
@Slf4j
public class RedisSentinelConfig {

    @Value("#{'${spring.redis.sentinel.nodes}'.split(',')}")
    private List<String> nodes;
    @Value("${spring.redis.sentinel.master}")
    private String myMasterName;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWait;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxWaitMillis(maxWait);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        return config;
    }

    /**
     * 第0个database
     * @author banjiawei 
     * @date 2020/10/10
     * @param 
     * @return org.springframework.data.redis.connection.RedisSentinelConfiguration
     */
    @Bean("redisSentinelConfiguration0")
    @Primary
    public RedisSentinelConfiguration redisSentinelConfiguration0(){
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        //配置matser的名称
        redisSentinelConfiguration.setMaster(myMasterName);
        //配置redis的哨兵sentinel
        redisSentinelConfiguration.setSentinels(sentinelNodes());
        redisSentinelConfiguration.setDatabase(0);
        return redisSentinelConfiguration;
    }

    /**
     * 第15个database
     * @author banjiawei
     * @date 2020/10/10
     * @param
     * @return org.springframework.data.redis.connection.RedisSentinelConfiguration
     */
    @Bean("redisSentinelConfiguration15")
    public RedisSentinelConfiguration redisSentinelConfiguration15(){
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        //配置matser的名称
        redisSentinelConfiguration.setMaster(myMasterName);
        //配置redis的哨兵sentinel
        redisSentinelConfiguration.setSentinels(sentinelNodes());
        redisSentinelConfiguration.setDatabase(15);
        return redisSentinelConfiguration;
    }

    @Bean(name = "jedisConnectionFactory0")
    @Primary
    public JedisConnectionFactory jedisConnectionFactory0(@Qualifier("redisSentinelConfiguration0") RedisSentinelConfiguration redisSentinelConfiguration, JedisPoolConfig jedisPoolConfig) {
        return new JedisConnectionFactory(redisSentinelConfiguration, jedisPoolConfig);
    }

    @Bean(name = "jedisConnectionFactory15")
    public JedisConnectionFactory jedisConnectionFactory15(@Qualifier("redisSentinelConfiguration15") RedisSentinelConfiguration redisSentinelConfiguration, JedisPoolConfig jedisPoolConfig) {
        return new JedisConnectionFactory(redisSentinelConfiguration, jedisPoolConfig);
    }

    @Bean(name = "redisTemplate0")
    @Primary
    public RedisTemplate<String, Object> redisTemplate0(@Qualifier("jedisConnectionFactory0") JedisConnectionFactory jedisConnectionFactory0){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory0);
        setRedisTemplateSerializer(redisTemplate);
        return redisTemplate;
    }

    @Bean(name = "redisTemplate15")
    public RedisTemplate<String, Object> redisTemplate15(@Qualifier("jedisConnectionFactory15") JedisConnectionFactory jedisConnectionFactory15){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory15);
        setRedisTemplateSerializer(redisTemplate);
        return redisTemplate;
    }

    /**
     * 获取redis的sentinel节点集合
     * @author banjiawei
     * @date 2020/10/10
     * @param
     * @return java.util.Set<org.springframework.data.redis.connection.RedisNode>
     */
    private Set<RedisNode> sentinelNodes(){
        Set<RedisNode> redisSentinelNodeSet = new HashSet<>();
        log.info("==========================nodes={}", nodes);
        nodes.forEach(node->{
            redisSentinelNodeSet.add(new RedisNode(node.split(":")[0],Integer.parseInt(node.split(":")[1])));
        });
        log.info("======================redisSentinelNodeSet -->"+redisSentinelNodeSet);
        return redisSentinelNodeSet;
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
