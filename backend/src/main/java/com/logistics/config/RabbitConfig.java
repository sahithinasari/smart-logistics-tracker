package com.logistics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitConfig {
    public static final String DELIVERY_STATUS_QUEUE = "delivery.status";

    @Bean
    public Queue deliveryStatusQueue() {
        return new Queue(DELIVERY_STATUS_QUEUE, false);
    }
}
