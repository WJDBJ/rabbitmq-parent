package com.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author XJ
 */
@Component
public class MySender {
    /**
     * 主要通过注入AmqpTemplate对象来实现消息的发送
     */
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "msg " + new Date();
        System.out.println("send : " + context);
        rabbitTemplate.convertAndSend("myQueue", context);
    }
}
