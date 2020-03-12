package com.sender;

import com.config.DirectConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author XJ
 */
@Component
public class DirectSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(int num) {
        String routeKey = "";
        if(num % 2 == 0) {
            routeKey = DirectConfig.ROUTE_KEY_1;
        }else {
            routeKey = DirectConfig.ROUTE_KEY_2;
        }
        amqpTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE_NAME,routeKey,
                "num："+ num + "，时间：" + LocalDateTime.now().
                        format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
