package com.logistics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // applies to all endpoints
                        .allowedOrigins("http://localhost:3000") // frontend origin
                        .allowedMethods("*") // GET, POST, PUT, DELETE, OPTIONS, etc.
                        .allowedHeaders("*")
                        .allowCredentials(true); // include cookies if needed
            }
        };
    }
}
