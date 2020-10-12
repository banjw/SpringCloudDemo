package com.learn.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * 测试随机数
 *
 * @author banjiawei
 * @date 2020/10/12
 */
@Slf4j
public class TestRandom {
    @Test
    public void test0(){
        for (int i = 0; i < 10; i++) {
            int a = ThreadLocalRandom.current().nextInt(1000);
            log.info("===================a:{}================", a);
            IntStream ints = ThreadLocalRandom.current().ints(4,1000,9999);
            log.info("ints={}", ints.toArray());
            int b = ThreadLocalRandom.current().nextInt(1000,9999);
            log.info("===================b:{}================", b);
        }
    }

    @Test
    public void test1(){
        for (int i = 0; i < 10; i++) {
            SplittableRandom random = new SplittableRandom();
            int a = random.nextInt(1000);
            log.info("------------a={}---------------", a);
            IntStream ints = random.ints(4,1000, 9999);
            log.info("------------ints={}---------------", ints.toArray());
            int b = random.nextInt(1000, 9999);
            log.info("------------b={}---------------", b);
        }
    }
}
