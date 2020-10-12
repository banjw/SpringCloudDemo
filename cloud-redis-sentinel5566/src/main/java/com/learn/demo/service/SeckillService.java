package com.learn.demo.service;

/**
 * @author banjiawei
 * @date 2020/10/12
 * 秒杀活动类
 */
public interface SeckillService {
    /**
     * 秒杀方法
     * @author banjiawei
     * @date 2020/10/12
     * @param
     * @return void
     */
    void flashSale();

    /**
     * 初始化库存
     * @author banjiawei
     * @date 2020/10/12
     * @param
     * @return void
     */
    void initStorage();
}
