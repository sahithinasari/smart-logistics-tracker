package com.logistics.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery_agents")
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String zone;
    private int currentLoad;
    public DeliveryAgent(String name, String zone, int currentLoad) {
        this.name = name;
        this.zone = zone;
        this.currentLoad = currentLoad;
    }
}