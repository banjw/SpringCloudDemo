package com.learn.demo.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsConsumer {
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
        MessageConsumer consumer = session.createConsumer(queue);
        while (true){
            TextMessage textMessage = (TextMessage) consumer.receive(3000L);
            if(null != textMessage){
                String text = textMessage.getText();
                System.out.println(text);
            }else {
                break;
            }
        }
        //释放资源
        consumer.close();
        session.close();
        connection.close();
        System.out.println("================结束获取消息==================");
    }
}
