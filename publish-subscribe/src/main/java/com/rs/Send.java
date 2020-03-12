package com.rs;

import com.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author XJ
 */
public class Send {
    //定义交换器名称

    private static final String EXCHANGE_NAME = "exchange";

    public static void main(String[] args) {
        try (Connection connection = ConnectionUtils.getConnection();
             Channel channel = connection.createChannel()){
            //声明一个交换机  fanout 分发
            channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

            String msg = "hello ps";
            channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
            System.out.println("msg = " + msg);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
