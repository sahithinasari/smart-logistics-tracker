package com.logistics.controller;

import com.logistics.dto.LoginRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody LoginRequest req) {
        String url = "http://localhost:8082/realms/smart-logistics-tracker/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("email", req.getEmail());
        body.put("password", req.getPassword());
        body.put("returnSecureToken", true);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

        String idToken = (String) response.getBody().get("idToken");
        return ResponseEntity.ok(idToken);
    }

}
