package com.learn.demo;

import com.learn.demo.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 秒杀测试类
 *
 * @author banjiawei
 * @date 2020/10/12
 */
@SpringBootTest
@Slf4j
public class TestSecKill {
    @Resource
    private SeckillService seckillService;

    @Test
    public void test(){
        seckillService.initStorage();
        for (int i = 0; i < 100; i++) {
            seckillService.flashSale();
        }
    }
}
