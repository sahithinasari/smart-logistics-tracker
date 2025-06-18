package com.logistics.controller;

import com.logistics.dto.OrderRequestDto;
import com.logistics.entity.Order;
import com.logistics.entity.OrderStatus;
import com.logistics.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(
            @RequestParam(required = false) String vendorId) {

        if (vendorId != null) {
            return ResponseEntity.ok(orderService.getOrdersByVendor(vendorId));
        }
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto dto) {
        Order savedOrder = orderService.createOrder(dto);
        return ResponseEntity.ok(savedOrder);
    }
    @GetMapping("/status")
    public ResponseEntity<List<Order>> getOrdersByStatus(@RequestParam(required = false) OrderStatus status) {
        if (status != null) {
            return ResponseEntity.ok(orderService.getOrdersByStatus(status));
        }
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @GetMapping("/failed")
    public ResponseEntity<List<Order>> getFailedOrders(@RequestParam(defaultValue = "5") int minRetries) {
        return ResponseEntity.ok(orderService.getFailedOrdersWithMinRetries(minRetries));
    }
}
