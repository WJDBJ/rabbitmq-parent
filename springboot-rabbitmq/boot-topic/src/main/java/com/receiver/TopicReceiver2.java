package com.receiver;

import com.config.TopicConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author XJ
 */
@Component
@RabbitListener(queues = TopicConfig.TOPIC_QUEUE_NAME_2)
public class TopicReceiver2 {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("TopicReceiver2 : " + msg);
    }
}
