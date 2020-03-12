package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author XJ
 */
@Configuration
public class TopicConfig {
    public static final String TOPIC_QUEUE_NAME_1 = "topic-queue-1";
    public static final String TOPIC_QUEUE_NAME_2 = "topic-queue-2";
    public static final String TOPIC_EXCHANGE_NAME = "topic-exchange";

    public static final String ROUTE_KEY_1 = "topic.#";
    public static final String ROUTE_KEY_2 = "topic.xj.*";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Queue queue1(){
        return new Queue(TOPIC_QUEUE_NAME_1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(TOPIC_QUEUE_NAME_2);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(topicExchange()).with(ROUTE_KEY_1);
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with(ROUTE_KEY_2);
    }
}
