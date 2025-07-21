package com.logistics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitConfig {
    public static final String DELIVERY_STATUS_QUEUE = "delivery.status";
    public static final String ORDER_CREATED_QUEUE = "orders.created"; // <-- new queue

    @Bean
    public Queue deliveryStatusQueue() {
        return new Queue(DELIVERY_STATUS_QUEUE, false);
    }

    @Bean
    public Queue orderCreatedQueue() {
        // durable queue so orders aren’t lost on broker restart
        return new Queue(ORDER_CREATED_QUEUE, true);
    }
}
