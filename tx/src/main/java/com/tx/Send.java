package com.tx;

import com.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author XJ
 */
public class Send {
    private static final String QUEUE_NAME = "queue_tx";

    public static void main(String[] args) {
        try(Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String msg = "hello tx";
            try {
                //开启事务
                channel.txSelect();
                channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
                //int x = 1/0;
                //提交事务
                channel.txCommit();
                System.out.println("事务提交");
            }catch (Exception e) {
                //回滚事务
                channel.txRollback();
                System.out.println("事务回滚");
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
