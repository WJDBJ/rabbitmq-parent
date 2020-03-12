package com.rout;

import com.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author XJ
 */
public class Send {
    private static final String EXCHANGE_NAME = "exchange_rout";

    public static void main(String[] args) {
        try (Connection connection = ConnectionUtils.getConnection();
             Channel channel = connection.createChannel()){
            //创建一个处理路由交换器
            channel.exchangeDeclare(EXCHANGE_NAME,"direct");
            //String RoutingKey = "error";
            String RoutingKey = "info";
            String msg = "Hello Routing "+RoutingKey;
            channel.basicPublish(EXCHANGE_NAME,RoutingKey,null,msg.getBytes());
            System.out.println("msg = " + msg);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
