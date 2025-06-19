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
@RequestMapping("/api/agent")
@PreAuthorize("hasRole('AGENT')")
public class AgentDashboardController {

    private final OrderService orderService;

    public AgentDashboardController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> agentSummary(@CurrentUsername String agentName) {
        Map<String, Object> summary = new HashMap<>();
        summary.put("assignedOrders", orderService.countByAgent(agentName));
        summary.put("pendingDeliveries", orderService.countByAgentAndStatus(agentName, OrderStatus.ASSIGNED));
        return ResponseEntity.ok(summary);
    }
}
