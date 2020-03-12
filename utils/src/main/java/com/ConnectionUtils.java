package com;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * 获取连接
 * @author XJ
 */
public class ConnectionUtils {
    /**
     * 返回MQ的连接
     * @return
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //获取一个连接
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务器地址
        factory.setHost("localhost");
        //设置协议地址
        factory.setPort(5672);
        //设置连接的虚拟机
        factory.setVirtualHost("/JN");
        //用户
        factory.setUsername("xj");
        //密码
        factory.setPassword("xj");
        return factory.newConnection();
    }
}
