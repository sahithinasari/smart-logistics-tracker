package com.logistics.seed;

import com.logistics.entity.DeliveryAgent;
import com.logistics.repository.DeliveryAgentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")  // ✅ Only runs when 'dev' profile is active
public class DevDataSeeder implements CommandLineRunner {

    private final DeliveryAgentRepository agentRepo;

    public DevDataSeeder(DeliveryAgentRepository agentRepo) {
        this.agentRepo = agentRepo;
    }

    @Override
    public void run(String... args) {
        if (agentRepo.count() == 0) {
            List<DeliveryAgent> agents = List.of(
                new DeliveryAgent("Ramesh", "ZoneA", 2),
                new DeliveryAgent("Suresh", "ZoneA", 1),
                new DeliveryAgent("Ganesh", "ZoneA", 3),
                new DeliveryAgent("Priya", "ZoneB", 0),
                new DeliveryAgent("Neha", "ZoneB", 1),
                new DeliveryAgent("Deepak", "ZoneC", 0),
                new DeliveryAgent("Nisha", "ZoneC", 2),
                new DeliveryAgent("Amit", "ZoneC", 1)
            );
            agentRepo.saveAll(agents);
            System.out.println("✅ Dev delivery agents seeded.");
        }
    }
}
