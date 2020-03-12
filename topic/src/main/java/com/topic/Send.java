package com.topic;

import com.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author XJ
 */
public class Send {
    private static final String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] args) {
        try (Connection connection = ConnectionUtils.getConnection();
             Channel channel = connection.createChannel()) {
            //定义交换器，主题 topic
            channel.exchangeDeclare(EXCHANGE_NAME,"topic");

            channel.basicQos(1);
            //                          推送至两个队列       推送至消费者1队列  推送至消费者2队列   不推送任何队列
            String[] RoutingKey  = {"quick.orange.rabbit","quick.orange.fox","lazy.brown.fox","quick.brown.fox"};
            for (String s : RoutingKey) {
                String msg = "hello topic " + s;
                channel.basicPublish(EXCHANGE_NAME, s, null, msg.getBytes());
                System.out.println("msg = " + msg);
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
