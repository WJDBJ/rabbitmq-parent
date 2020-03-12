package com.nf.controller;
import com.ConnectionUtils;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author XJ
 */
public class Send {
    private final static String QUEUE_NAME = "hello";
    public static void main(String[] args) {
        //获取一个连接
        try (Connection connection = ConnectionUtils.getConnection();
             //获取一个通道
             Channel channel = connection.createChannel();){
            //创建一个队列，下面的参数分别是：
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String msg = "hello world";
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println("send msg = " + msg);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
