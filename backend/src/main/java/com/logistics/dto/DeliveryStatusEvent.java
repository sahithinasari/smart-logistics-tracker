package com.logistics.dto;


import com.logistics.entity.OrderStatus;
import lombok.Data;

@Data
public class DeliveryStatusEvent {
    private Long orderId;
    private OrderStatus status;
    // Getters and Setters
}
