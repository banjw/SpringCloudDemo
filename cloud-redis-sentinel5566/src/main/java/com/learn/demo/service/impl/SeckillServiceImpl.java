package com.learn.demo.service.impl;

import com.learn.demo.entity.UserEntity;
import com.learn.demo.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author banjiawei
 * @date 2020/10/12
 * @description
 */
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {
    private static final String STORAGE_KEY = "SecKill:Storage:Key";
    private static final String USER_KEY = "Seckill:User:Key";
    @Resource
    @Qualifier("redisTemplate0")
    private RedisTemplate<String, Object> redisTemplate0;

    @Override
    public String flashSale() {
        int userId = ThreadLocalRandom.current().nextInt(1000, 9999);
        UserEntity user = new UserEntity();
        user.setId(userId);
        if(redisTemplate0.opsForSet().isMember(USER_KEY, user.toString())){
            log.info("不能重复抢购");
            return "不能重复抢购";
        }
        if(Integer.parseInt(redisTemplate0.opsForValue().get(STORAGE_KEY).toString()) <= 0){
            log.info("抢购失败");
            return "抢购失败";
        }
        Long decrement = redisTemplate0.opsForValue().decrement(STORAGE_KEY);
        redisTemplate0.opsForSet().add(USER_KEY, user.toString());
        log.info("抢购成功，userId:{}, 库存为:{}", userId, decrement);
        return String.format("抢购成功，userId:%s, 库存为:%s", userId, decrement);
    }

    @Override
    public void initStorage() {
        redisTemplate0.opsForValue().set(STORAGE_KEY, "100");
        log.info("初始化抢购库存成功，库存为100");
    }
}
