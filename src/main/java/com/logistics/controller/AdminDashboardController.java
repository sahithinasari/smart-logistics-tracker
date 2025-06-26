package com.logistics.controller;

import com.logistics.entity.DeliveryAgent;
import com.logistics.entity.Order;
import com.logistics.entity.OrderStatus;
import com.logistics.repository.DeliveryAgentRepository;
import com.logistics.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardController {

    private final OrderService orderService;
    private final DeliveryAgentRepository agentRepo;

    public AdminDashboardController(OrderService orderService, DeliveryAgentRepository agentRepo) {
        this.orderService = orderService;
        this.agentRepo = agentRepo;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> adminSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalOrders", orderService.countAll());
        summary.put("failedOrders", orderService.countByStatus(OrderStatus.FAILED));
        summary.put("agents", agentRepo.count());
        summary.put("zones", agentRepo.findAll().stream().map(DeliveryAgent::getZone).distinct().count());
        return ResponseEntity.ok(summary);
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
