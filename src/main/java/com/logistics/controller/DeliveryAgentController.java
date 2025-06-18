package com.logistics.controller;

import com.logistics.dto.DeliveryAgentDto;
import com.logistics.entity.DeliveryAgent;
import com.logistics.repository.DeliveryAgentRepository;
import com.logistics.util.ZoneMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class DeliveryAgentController {

    private final DeliveryAgentRepository agentRepository;

    public DeliveryAgentController(DeliveryAgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @PostMapping
    public ResponseEntity<DeliveryAgent> addAgent(@RequestBody DeliveryAgentDto dto) {
        DeliveryAgent agent = new DeliveryAgent();
        agent.setName(dto.getName());
        agent.setZone(dto.getZone());
        agent.setCurrentLoad(0);
        return ResponseEntity.status(201).body(agentRepository.save(agent));
    }

    @GetMapping
    public ResponseEntity<List<DeliveryAgent>> getAllAgents() {
        return ResponseEntity.ok(agentRepository.findAll());
    }
}