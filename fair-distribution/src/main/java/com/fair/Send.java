package com.fair;

import com.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author XJ
 */
public class Send {
    private static final String QUEUE_NAME = "round";

    public static void main(String[] args) {
        //获取连接
        try (Connection connection = ConnectionUtils.getConnection();
             //获取通道
             Channel channel = connection.createChannel()){
            //创建队列
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //限制发送消息个数
            channel.basicQos(1);

            for (int i = 0; i < 50; i++) {
                String msg = "hello "+i;
                channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
                Thread.sleep(i*5);
                System.out.println("msg = " + msg);
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
