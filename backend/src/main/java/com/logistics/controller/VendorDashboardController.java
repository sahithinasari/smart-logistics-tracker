package com.logistics.controller;

import com.logistics.annotation.CurrentUsername;
import com.logistics.entity.OrderStatus;
import com.logistics.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/vendor")
@PreAuthorize("hasRole('VENDOR')")
public class VendorDashboardController {

    private final OrderService orderService;

    public VendorDashboardController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> vendorSummary(@CurrentUsername String vendorEmail) {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalOrders", orderService.countByVendor(vendorEmail));
        summary.put("failedOrders", orderService.countByVendorAndStatus(vendorEmail, OrderStatus.FAILED));
        summary.put("assignedOrders", orderService.countByVendorAndStatus(vendorEmail, OrderStatus.ASSIGNED));
        summary.put("deliveredOrders", orderService.countByVendorAndStatus(vendorEmail, OrderStatus.DELIVERED));
        return ResponseEntity.ok(summary);
    }
}
