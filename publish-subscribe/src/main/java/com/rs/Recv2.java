package com.rs;

import com.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author XJ
 */
public class Recv2 {
    private static final String QUEUE_NAME = "Queue_Recv2";
    private static final String EXCHANGE_NAME = "exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        channel.basicQos(1);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("Recv2 msg = " + msg);
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        boolean autoArk = false;
        channel.basicConsume(QUEUE_NAME,autoArk,defaultConsumer);
    }
}
