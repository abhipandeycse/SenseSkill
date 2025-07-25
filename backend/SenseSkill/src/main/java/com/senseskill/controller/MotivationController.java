package com.senseskill.controller;

import com.senseskill.model.MotivationAnalysis;
import com.senseskill.model.User;
import com.senseskill.repository.MotivationAnalysisRepository;
import com.senseskill.repository.UserRepository;
import com.senseskill.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MotivationController {

	@Autowired private MotivationAnalysisRepository motivationRepo;
	@Autowired private UserRepository userRepo;
	@Autowired private JwtUtil jwtUtil;

    @PostMapping("/analyze/motivation")
    public ResponseEntity<?> analyzeMotivation(@RequestBody Map<String, String> payload, HttpServletRequest request) {
        String text = payload.get("text");

        // Call Flask motivation NLP endpoint
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> flaskPayload = new HashMap<>();
        flaskPayload.put("text", text);

        Map<String, Object> flaskResponse = restTemplate.postForObject(
                "http://localhost:5000/analyze/motivation", flaskPayload, Map.class);

        // Extract fields
        String tone = (String) flaskResponse.get("tone");
        String suggestedStyle = (String) flaskResponse.get("suggested_style");

        // Extract user
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.extractUsername(token);

        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) return ResponseEntity.status(401).body("Invalid user.");

        User user = userOpt.get();

        // Save to DB
        MotivationAnalysis analysis = MotivationAnalysis.builder()
                .inputText(text)
                .tone(tone)
                .suggestedStyle(suggestedStyle)
                .analyzedAt(LocalDateTime.now())
                .user(user)
                .build();

        motivationRepo.save(analysis);

        return ResponseEntity.ok(flaskResponse);
    }

    @GetMapping("/motivation/history")
    public ResponseEntity<?> getMotivationHistory(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.extractUsername(token);

        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) return ResponseEntity.status(401).body("Invalid user.");

        return ResponseEntity.ok(motivationRepo.findByUser(userOpt.get()));
    }
}
