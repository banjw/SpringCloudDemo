package com.learn.demo.controller;

import com.learn.demo.server.EsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EsController {

    @Autowired
    private EsServer server;

    @GetMapping("/search/es/index")
    public String index(){
        return "OK";
    }

    @GetMapping("/search/es/createindex/{indexName}")
    public String createIndex(@PathVariable("indexName") String indexName){
        try {
            boolean flag = server.createIndex(indexName);
            if(flag){
                return String.format("创建索引[%s]成功", indexName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.format("创建索引[%s]失败", indexName);
    }

    @GetMapping("/search/es/validindex/{indexName}")
    public String validIndex(@PathVariable("indexName") String indexName){
        try {
            boolean flag = server.validIndex(indexName);
            if(flag){
                return String.format("索引[%s]存在", indexName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.format("索引[%s]不存在或查询出错", indexName);
    }
}
