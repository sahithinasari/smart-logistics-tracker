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


}