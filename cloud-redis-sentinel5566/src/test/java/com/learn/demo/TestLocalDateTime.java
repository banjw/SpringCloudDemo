package com.learn.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 时间测试类
 *
 * @author banjiawei
 * @date 2020/10/12
 */
@Slf4j
public class TestLocalDateTime {
    @Test
    public void testDate2LocalDateTime(){
        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        log.info("data:{},localDateTime:{}", date, localDateTime);
    }

    @Test
    public void testLocalDateTime2TimeStr(){
        String monthAndDay = "MM月dd日 HH:mm:ss";
        LocalDateTime now = LocalDateTime.now();
        String monthAndDayStr = now.format(DateTimeFormatter.ofPattern(monthAndDay));
        log.info("monthAndDayStr:{}", monthAndDayStr);
        String yearMonthAndDay = "yyyy年 MM月dd日 HH时mm分ss秒";
        String yearMonthAndDayStr = now.format(DateTimeFormatter.ofPattern(yearMonthAndDay));
        log.info("yearMonthAndDayStr:{}", yearMonthAndDayStr);
    }

    @Test
    public void testCompareLocalDateTime() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(3);
        LocalDateTime now1 = LocalDateTime.now();
        int a = now.compareTo(now1);
        log.info("=============now.compareTo(now1):{}====================",a);
        int b = now1.compareTo(now);
        log.info("=============now1.compareTo(now):{}====================",b);
        int c = now1.compareTo(now1);
        log.info("=============now1.compareTo(now1):{}====================",c);
    }
}
