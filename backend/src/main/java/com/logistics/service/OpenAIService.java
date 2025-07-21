package com.logistics.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Map;

@Service
public class OpenAIService {

    private WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .build();
    }

    public String getChatResponse(String userMessage) {
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of("role", "user", "content", userMessage)
                )
        );

        try {
            String rawResponse = webClient.post()
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // Parse JSON and extract the assistant's message
            JsonNode root = objectMapper.readTree(rawResponse);
            String content = root.path("choices").get(0).path("message").path("content").asText();

            return objectMapper.writeValueAsString(Map.of("response", content));

        } catch (WebClientResponseException e) {
            System.err.println("Error Response: " + e.getResponseBodyAsString());
            return "{\"error\":\"API call failed: " + e.getMessage() + "\"}";
        } catch (Exception e) {
            System.err.println("Error parsing response: " + e.getMessage());
            return "{\"error\":\"Unexpected error occurred\"}";
        }
    }
}
