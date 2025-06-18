package com.logistics.repository;

import com.logistics.entity.DeliveryAgent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long> {
    List<DeliveryAgent> findByZone(String zone);
    Optional<DeliveryAgent> findFirstByZoneAndCurrentLoadLessThanOrderByCurrentLoadAsc(String zone, int maxLoad);

}