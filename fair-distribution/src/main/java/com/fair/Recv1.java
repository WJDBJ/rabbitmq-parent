package com.fair;

import com.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author XJ
 */
public class Recv1 {
    private static final String QUEUE_NAME = "round";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("Recv1 msg = " + msg);
                    //手动反馈
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        //autoAck = true表示RabbitMQ启动了自动确认模式，
        //该模式下RabbitMQ一旦将消息发给消费者，该消息将会从内存中删除,
        //如果消费者突然崩溃或者结束进程了，该消息就会丢失
        //------------------------------------------------
        //而autoAck = false手动确认，就会将该消息交给其他消费者。
        //消费者接收后就会发送一个消息回执告诉RabbitMQ消息处理完毕，然后RabbitMQ才会删除该消息
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME,autoAck,defaultConsumer);
    }
}
