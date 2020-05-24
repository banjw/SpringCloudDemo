package com.learn.demo.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancerDemo {
    /**
     * 获取注册中心的服务，通过算法选择调用实例
     * @param serviceInstances
     * @return
     */
    ServiceInstance getInstance(List<ServiceInstance> serviceInstances);
}
