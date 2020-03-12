package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author XJ
 */
@Configuration
public class FanoutConfig {
    /**
     * 定义交换器名和队列名
     */
    public static final String FANOUT_QUEUE_NAME_1 = "fanout-queue-1";
    public static final String FANOUT_QUEUE_NAME_2 = "fanout-queue-2";
    public static final String FANOUT_EXCHANGE_NAME = "fanout-exchange";

    @Bean
    public Queue queue1() {
        return new Queue(FANOUT_QUEUE_NAME_1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(FANOUT_QUEUE_NAME_2);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
    }

    /**
     * 绑定队列到交换器
     * @return
     */
    @Bean
    public Binding bindingQueue1(){
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingQueue2(){
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }
}
