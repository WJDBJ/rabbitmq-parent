package com.nf.controller;

import com.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author XJ
 */
public class Recv {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        //获取一个通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        DeliverCallback deliverCallback = getDeliverCallback();
        DefaultConsumer defaultConsumer = getDefaultConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,defaultConsumer);
//        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

    //定义消费者
    private static DefaultConsumer getDefaultConsumer(Channel channel) {
        //获取到达的消息
        return new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body,"utf-8");
                    System.out.println(msg);
                }
            };
    }

    private static DeliverCallback getDeliverCallback() {
        return (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };
    }
}
