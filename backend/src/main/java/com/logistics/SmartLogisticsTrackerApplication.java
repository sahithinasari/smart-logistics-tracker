package com.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartLogisticsTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartLogisticsTrackerApplication.class, args);
	}

}
