package com.logistics.scheduler;

import com.logistics.entity.DeliveryAgent;
import com.logistics.entity.Order;
import com.logistics.entity.OrderStatus;
import com.logistics.repository.OrderRepository;
import com.logistics.service.DeliveryAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetryFailedOrdersScheduler {

    private static final Logger log = LoggerFactory.getLogger(RetryFailedOrdersScheduler.class);

    private final OrderRepository orderRepository;
    private final DeliveryAssignmentService assignmentService;

    @Value("${assignment.max-retries:5}")
    private int maxRetries;

    public RetryFailedOrdersScheduler(OrderRepository orderRepository, DeliveryAssignmentService assignmentService) {
        this.orderRepository = orderRepository;
        this.assignmentService = assignmentService;
    }

  //  @Scheduled(fixedDelay = 60000) // Every 60 seconds
    public void retryFailedOrders() {
        List<Order> failedOrders = orderRepository.findAll().stream()
                .filter(order -> order.getStatus() == OrderStatus.FAILED)
                .toList();

        if (failedOrders.isEmpty()) {
            log.info("✅ No failed orders to retry.");
            return;
        }

        log.info("🔁 Retrying {} failed orders...", failedOrders.size());

        for (Order order : failedOrders) {
            if (order.getRetryCount() >= maxRetries) {
                log.warn("🚫 Max retries reached for Order ID {} ({} retries). Skipping.", order.getId(), order.getRetryCount());
                continue;
            }

            order.setRetryCount(order.getRetryCount() + 1);
            DeliveryAgent agent = assignmentService.assignAgent(order.getDeliveryZone());

            if (agent != null) {
                order.setAssignedAgent(agent);
                order.setStatus(OrderStatus.ASSIGNED);
                log.info("✅ Reassigned Order ID {} to Agent {} (Retry #{})", order.getId(), agent.getName(), order.getRetryCount());
            } else {
                log.warn("⚠️ Still no agents for Order ID {} in zone {} (Retry #{})", order.getId(), order.getDeliveryZone(), order.getRetryCount());
            }

            orderRepository.save(order);
        }
    }
}
