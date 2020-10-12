package com.learn.demo.controller;

import com.learn.demo.service.SeckillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 秒杀控制层类
 *
 * @author banjiawei
 * @date 2020/10/12
 */
@RestController
public class SeckillController {

    @Resource
    private SeckillService seckillService;

    @GetMapping("/flash/sale")
    public void flashSale(){
        seckillService.flashSale();
    }
}
