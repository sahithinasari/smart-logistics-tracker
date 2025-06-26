package com.logistics.controller;

import com.logistics.dto.OrderRequestDto;
import com.logistics.entity.Order;
import com.logistics.entity.OrderStatus;
import com.logistics.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('VENDOR')")
    public ResponseEntity<List<Order>> getAllOrders(
            @RequestParam(required = false) String vendorId) {

        if (vendorId != null) {
            return ResponseEntity.ok(orderService.getOrdersByVendor(vendorId));
        }
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto dto) {
        Order savedOrder = orderService.createOrder(dto);
        return ResponseEntity.ok(savedOrder);
    }
}
