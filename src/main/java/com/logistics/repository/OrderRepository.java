package com.logistics.repository;

import com.logistics.entity.Order;
import com.logistics.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByVendorId(String vendorId);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByStatusAndRetryCountGreaterThanEqual(OrderStatus status, int retryCount);
    long countByVendorId(String vendorId);
    long countByVendorIdAndStatus(String vendorId, OrderStatus status);

    long countByStatus(OrderStatus status);

    long countByAssignedAgentName(String agentName);
    long countByAssignedAgentNameAndStatus(String agentName, OrderStatus status);


}