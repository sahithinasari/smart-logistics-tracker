package com.logistics.controller;

import com.logistics.dto.ChatRequest;
import com.logistics.service.OpenAIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final OpenAIService openAIService;

    public ChatController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        return openAIService.getChatResponse(request.getMessage());
    }
}


