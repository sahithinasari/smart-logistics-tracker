package com.logistics.service;

import com.logistics.dto.OrderRequestDto;
import com.logistics.entity.DeliveryAgent;
import com.logistics.entity.Order;
import com.logistics.entity.OrderStatus;
import com.logistics.exception.order.OrderNotFoundException;
import com.logistics.repository.OrderRepository;
import com.logistics.util.ZoneMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryAssignmentService assignmentService;

    public OrderService(OrderRepository orderRepository,DeliveryAssignmentService assignmentService) {
        this.orderRepository = orderRepository;
        this.assignmentService=assignmentService;
    }
    public Order createOrder(OrderRequestDto dto) {
        Order order = new Order();
        order.setVendorId(dto.getVendorId());
        order.setCustomerName(dto.getCustomerName());
        order.setDeliveryAddress(dto.getDeliveryAddress());
        String zone = ZoneMapper.getZoneForAddress(order.getDeliveryAddress());
        order.setDeliveryZone(zone);
        DeliveryAgent agent = assignmentService.assignAgent(zone);
        order.setAssignedAgent(agent);
        order.setStatus(agent == null ? OrderStatus.FAILED : OrderStatus.ASSIGNED);
        return orderRepository.save(order);
    }
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException(id));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByVendor(String vendorId) {
        return orderRepository.findByVendorId(vendorId);
    }
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }
    public List<Order> getFailedOrdersWithMinRetries(int retries) {
        return orderRepository.findByStatusAndRetryCountGreaterThanEqual(OrderStatus.FAILED,retries);
    }
    // -------- VENDOR Dashboard Methods --------
    public long countByVendor(String vendorId) {
        return orderRepository.countByVendorId(vendorId);
    }

    public long countByVendorAndStatus(String vendorId, OrderStatus status) {
        return orderRepository.countByVendorIdAndStatus(vendorId, status);
    }

    // -------- ADMIN Dashboard Methods --------
    public long countAll() {
        return orderRepository.count();
    }

    public long countByStatus(OrderStatus status) {
        return orderRepository.countByStatus(status);
    }

    // -------- AGENT Dashboard Methods --------
    public long countByAgent(String agentName) {
        return orderRepository.countByAssignedAgentName(agentName);
    }

    public long countByAgentAndStatus(String agentName, OrderStatus status) {
        return orderRepository.countByAssignedAgentNameAndStatus(agentName, status);
    }

}