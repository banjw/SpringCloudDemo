package com.learn.demo.zone;

import java.time.ZonedDateTime;

public class Test {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
        System.out.println(now.toLocalDate());
    }
}
