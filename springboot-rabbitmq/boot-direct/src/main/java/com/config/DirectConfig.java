package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author XJ
 */
@Configuration
public class DirectConfig {
    public static final String DIRECT_QUEUE_NAME_1 = "direct-queue-1";
    public static final String DIRECT_QUEUE_NAME_2 = "direct-queue-2";
    public static final String DIRECT_EXCHANGE_NAME = "direct-exchange";

    public static final String ROUTE_KEY_1 = "direct.route.key.1";
    public static final String ROUTE_KEY_2 = "direct.route.key.2";

    @Bean
    public Queue directQueue1(){
        return new Queue(DIRECT_QUEUE_NAME_1);
    }

    @Bean
    public Queue directQueue2(){
        return new Queue(DIRECT_QUEUE_NAME_2);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    /**
     * 绑定队列到交换器并定义路由键
     * @return
     */
    @Bean
    public Binding bindingQueue1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with(ROUTE_KEY_1);
    }

    @Bean
    public Binding bindingQueue2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with(ROUTE_KEY_2);
    }
}
