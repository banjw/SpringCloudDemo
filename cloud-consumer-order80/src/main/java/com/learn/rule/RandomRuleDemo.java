package com.learn.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomRuleDemo {

    //返回想要使用的规则实体对象
    @Bean
    public IRule getRandomRule(){
        return new RandomRule();
    }
}
