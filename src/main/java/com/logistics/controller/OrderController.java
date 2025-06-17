package com.logistics.controller;

import com.logistics.dto.OrderRequestDto;
import com.logistics.entity.Order;
import com.logistics.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto dto) {
        Order savedOrder = orderService.createOrder(dto);
        return ResponseEntity.ok(savedOrder);
    }
}
