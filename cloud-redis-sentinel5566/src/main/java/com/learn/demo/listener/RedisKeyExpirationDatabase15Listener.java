package com.learn.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author banjiawei
 * @date 2020/10/10
 * Redis监听器
 */
@Component
@Slf4j
public class RedisKeyExpirationDatabase15Listener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationDatabase15Listener(@Qualifier("redisMessageListenerContainer15") RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }
    
    /**
     * 针对redis数据失效事件，进行数据处理
     * @author banjiawei 
     * @date 2020/10/10
     * @param message
     * @param pattern
     * @return void
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        if(expiredKey.startsWith("15:Order:")){
            //如果是Order:开头的key，进行处理
            log.info("第15个数据库================================expiredKey:{}",expiredKey);
        }
    }
}
