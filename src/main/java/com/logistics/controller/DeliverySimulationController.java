package com.logistics.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.logistics.dto.DeliveryStatusEvent;
import com.logistics.entity.OrderStatus;
import com.logistics.messaging.DeliveryEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deliver")
@Slf4j
public class DeliverySimulationController {

    private final DeliveryEventProducer producer;

    public DeliverySimulationController(DeliveryEventProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/{orderId}/{status}")
    @PreAuthorize("hasRole('AGENT') or hasRole('ADMIN')")
    public ResponseEntity<String> simulateDelivery(@PathVariable Long orderId, @PathVariable OrderStatus status) throws JsonProcessingException {
        DeliveryStatusEvent event = new DeliveryStatusEvent();
        event.setOrderId(orderId);
        event.setStatus(status);
        producer.sendStatusUpdate(event);
        return ResponseEntity.ok("Delivery update event sent for Order ID " + orderId);
    }
}
