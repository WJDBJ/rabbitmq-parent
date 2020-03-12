package com.sender;

import com.config.FanoutConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author XJ
 */
@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String msg){
        amqpTemplate.convertAndSend(FanoutConfig.FANOUT_EXCHANGE_NAME,"",msg);
    }
}
