package com.learn.demo.lb.impl;

import com.learn.demo.lb.LoadBalancerDemo;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoadBalancerDemoImpl implements LoadBalancerDemo {

    private AtomicInteger indexInteger = new AtomicInteger(0);

    /**
     * 使用自旋锁获取访问次数
     * @return
     */
    private int getNextIndex(){
        int current;
        int next;
        do{
            current = indexInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!indexInteger.compareAndSet(current, next));
        System.out.println(String.format("当前是第[%s]次访问", next));
        return next;
    }

    @Override
    public ServiceInstance getInstance(List<ServiceInstance> serviceInstances) {
        int index = getNextIndex() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
