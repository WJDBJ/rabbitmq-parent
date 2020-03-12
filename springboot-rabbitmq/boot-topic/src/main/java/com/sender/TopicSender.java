package com.sender;

import com.config.TopicConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author XJ
 */
@Component
public class TopicSender {

    private static final String TOPIC_PREFIX = "topic.";

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String selector,String msg) {
        amqpTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE_NAME,
                TOPIC_PREFIX+selector,msg+",当前时间：" + LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
