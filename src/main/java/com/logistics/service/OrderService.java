package com.logistics.service;

import com.logistics.dto.OrderRequestDto;
import com.logistics.entity.Order;
import com.logistics.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(OrderRequestDto dto) {
        Order order = new Order();
        order.setVendorId(dto.getVendorId());
        order.setCustomerName(dto.getCustomerName());
        order.setDeliveryAddress(dto.getDeliveryAddress());
        order.setDeliveryZone(dto.getDeliveryZone());
        return orderRepository.save(order);
    }
}