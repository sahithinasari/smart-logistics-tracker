package com.logistics.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.config.RabbitConfig;
import com.logistics.dto.OrderRequestDto;
import com.logistics.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.amqp.dsl.Amqp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class OrderIngestionIntegrationConfig {

    private static final Logger log = LoggerFactory.getLogger(OrderIngestionIntegrationConfig.class);

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    public OrderIngestionIntegrationConfig(ObjectMapper objectMapper, OrderService orderService) {
        this.objectMapper = objectMapper;
        this.orderService = orderService;
    }

    @Bean
    public IntegrationFlow orderIngestionFlow(ConnectionFactory connectionFactory) {
        return IntegrationFlow
                .from(Amqp.inboundAdapter(connectionFactory, RabbitConfig.ORDER_CREATED_QUEUE))
                .transform(byte[].class, bytes -> {
                    try {
                        return objectMapper.readValue(bytes, OrderRequestDto.class);
                    } catch (Exception e) {
                        log.error("Invalid order message: {}", new String(bytes), e);
                        return null;
                    }
                })
                .filter(dto -> dto != null)
                .handle(OrderRequestDto.class, (dto, headers) -> {
                    log.info("📥 Received new order for vendor {}", dto.getVendorId());
                    orderService.createOrder(dto);
                    return null;
                })
                .get();
    }
//    private OrderRequestDto jsonToOrder(Message<?> message) {
//        try {
//            String json = (String) message.getPayload();
//            return objectMapper.readValue(json, OrderRequestDto.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Invalid JSON in Notification", e);
//        }
//    }
}
