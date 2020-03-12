package com.rout;

import com.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author XJ
 */
public class Recv2 {
    private static final String EXCHANGE_NAME = "exchange_rout";
    private static final String QUEUE_NAME = "Recv2_Rout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        channel.basicQos(1);
        //绑定交换器并定义多个key值
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"error");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"info");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"warning");

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("Recv2 msg = " + msg);
                    //手动反馈
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME,autoAck,defaultConsumer);
    }
}
