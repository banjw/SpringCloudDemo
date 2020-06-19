package com.learn.demo.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProducer {
    private static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "testQueue";
    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //通过工厂获取连接
        Connection connection = factory.createConnection();
        connection.start();
        //创建会话session，第一个叫事务，第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Queue queue = session.createQueue(QUEUE_NAME);
        //消息放入队列
        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i < 3 ; i++) {
            //编写消息
            TextMessage textMessage = session.createTextMessage(String.format("第[%d]条消息", i));
            //发送消息
            producer.send(textMessage);
        }
        //释放资源
        producer.close();
        session.close();
        connection.close();
        System.out.println("================结束发送消息==================");
    }
}
