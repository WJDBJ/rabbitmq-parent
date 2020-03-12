package com.receiver;

import com.config.DirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author XJ
 */
@Component
@RabbitListener(queues = DirectConfig.DIRECT_QUEUE_NAME_1)
public class DirectReceiver1 {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("DirectReceiver1 : " + msg);
    }
}
