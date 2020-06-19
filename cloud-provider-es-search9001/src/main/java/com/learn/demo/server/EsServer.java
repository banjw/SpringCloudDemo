package com.learn.demo.server;


import com.learn.demo.entities.Payment;

import java.io.IOException;

public interface EsServer {
    /**
     * 创建索引
     * @param indexName
     * @return
     */
    boolean createIndex(String indexName) throws IOException;

    boolean validIndex(String indexName) throws IOException;

    boolean deleteIndex(String indexName) throws IOException;

    void addDocument(Payment payment, String indexName, String id) throws IOException;

    void volidDocument(String indexName, String id) throws IOException;

    void getDocument(String indexName, String id) throws IOException;

}
