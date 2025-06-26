package com.logistics.service;

import com.logistics.entity.DeliveryAgent;
import com.logistics.repository.DeliveryAgentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeliveryAssignmentService {

    private final DeliveryAgentRepository agentRepository;

    public DeliveryAssignmentService(DeliveryAgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public DeliveryAgent assignAgent(String zone) {
        int maxAllowedLoad = 5; // Or load from config using @Value
        return agentRepository
                .findFirstByZoneAndCurrentLoadLessThanOrderByCurrentLoadAsc(zone, maxAllowedLoad)
                .map(agent -> {
                    agent.setCurrentLoad(agent.getCurrentLoad() + 1);
                    return agentRepository.save(agent);
                })
                .orElse(null); // Fallback will handle this as a FAILED order
    }
}