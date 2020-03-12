package com.receiver;

import com.config.FanoutConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author XJ
 */
@Component
@RabbitListener(queues = FanoutConfig.FANOUT_QUEUE_NAME_2)
public class FanoutReceiver2 {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("FanoutReceiver2 : " + msg);
    }
}
