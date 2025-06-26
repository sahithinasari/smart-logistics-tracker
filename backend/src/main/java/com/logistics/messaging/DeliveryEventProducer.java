package com.logistics.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.config.RabbitConfig;
import com.logistics.dto.DeliveryStatusEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryEventProducer {
    @Autowired
    private ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public DeliveryEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendStatusUpdate(DeliveryStatusEvent event) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(event);
        rabbitTemplate.convertAndSend(RabbitConfig.DELIVERY_STATUS_QUEUE, json);
    }
}
