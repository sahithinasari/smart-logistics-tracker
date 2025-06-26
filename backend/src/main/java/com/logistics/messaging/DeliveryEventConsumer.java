package com.logistics.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.config.RabbitConfig;
import com.logistics.dto.DeliveryStatusEvent;
import com.logistics.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryEventConsumer {

    private final OrderRepository orderRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public DeliveryEventConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RabbitListener(queues = RabbitConfig.DELIVERY_STATUS_QUEUE)
    public void handleDeliveryUpdate(String event) throws JsonProcessingException {
        DeliveryStatusEvent deliveryStatusEvent=objectMapper.readValue(event,DeliveryStatusEvent.class);
        orderRepository.findById(deliveryStatusEvent.getOrderId()).ifPresent(order -> {
            order.setStatus(deliveryStatusEvent.getStatus());
            orderRepository.save(order);
        });
    }
}
