package com.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author XJ
 * 这里指定从已经创建好的队列myQueue里读取信息
 */
@Component
@RabbitListener(queues= "myQueue")
public class MyReceiver {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }
}
