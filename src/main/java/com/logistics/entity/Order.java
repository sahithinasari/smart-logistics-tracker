package com.logistics.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorId;
    private String customerName;
    private String deliveryAddress;
    private String deliveryZone;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
}