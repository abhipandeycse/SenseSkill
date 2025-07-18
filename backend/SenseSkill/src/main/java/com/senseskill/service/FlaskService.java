package com.senseskill.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlaskService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> analyzeText(String text) {
        String url = "http://localhost:5000/analyze";
        Map<String, String> request = Map.of("text", text);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        return response.getBody();
    }
}
