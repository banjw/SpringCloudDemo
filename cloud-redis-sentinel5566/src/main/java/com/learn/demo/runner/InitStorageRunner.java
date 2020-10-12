package com.learn.demo.runner;

import com.learn.demo.service.SeckillService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 初始化抢购库存
 *
 * @author banjiawei
 * @date 2020/10/12
 */
@Component
public class InitStorageRunner implements ApplicationRunner {
    @Resource
    private SeckillService seckillService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        seckillService.initStorage();
    }
}
