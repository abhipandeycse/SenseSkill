package com.senseskill.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senseskill.model.EmotionAnalysis;
import com.senseskill.model.MotivationAnalysis;
import com.senseskill.model.QuizResult;
import com.senseskill.model.Recommendation;
import com.senseskill.model.User;
import com.senseskill.repository.EmotionAnalysisRepository;
import com.senseskill.repository.MotivationAnalysisRepository;
import com.senseskill.repository.QuizResultRepository;
import com.senseskill.repository.RecommendationRepository;
import com.senseskill.repository.UserRepository;
import com.senseskill.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecommendationController {

	@Autowired private JwtUtil jwtUtil;
	@Autowired private UserRepository userRepo;
	@Autowired private EmotionAnalysisRepository emotionRepo;
	@Autowired private MotivationAnalysisRepository motivationRepo;
	@Autowired private QuizResultRepository quizResultRepo;
	@Autowired private RecommendationRepository recommendationRepo;

    @GetMapping("/recommendations")
    public ResponseEntity<?> getRecommendations(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.extractUsername(token);

        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) return ResponseEntity.status(401).body("Invalid user");
        User user = userOpt.get();

        // Get latest emotion
        EmotionAnalysis emotion = emotionRepo.findTopByUserOrderByAnalyzedAtDesc(user);
        String detectedEmotion = (emotion != null) ? emotion.getEmotion() : "neutral";

        // Get latest motivation
        MotivationAnalysis motivation = motivationRepo.findTopByUserOrderByAnalyzedAtDesc(user);
        String userMotivation = (motivation != null) ? motivation.getInputText() : "unspecified";

        // Get latest quiz result
        QuizResult quizResult = quizResultRepo.findTopByUserOrderByTakenAtDesc(user);
        String strength = (quizResult != null) ? quizResult.getDominantStrength() : "general";

        //  Mock Logic: Replace this with real AI or search API
        List<String> recommendations = new ArrayList<>();
        if ("confused".equals(detectedEmotion) && "logical".equalsIgnoreCase(strength)) {
            recommendations.add("https://github.com/karan/Projects");
            recommendations.add("https://www.geeksforgeeks.org/data-structures/");
            recommendations.add("https://www.youtube.com/watch?v=RBSGKlAvoiM");
        } else if ("driven".equals(detectedEmotion) && "technical".equalsIgnoreCase(strength)) {
            recommendations.add("https://roadmap.sh/backend");
            recommendations.add("https://www.udemy.com/course/machinelearning/");
        } else {
            recommendations.add("https://www.coursera.org/");
            recommendations.add("https://developer.mozilla.org/en-US/");
        }

        // Save recommendation
        Recommendation saved = recommendationRepo.save(
                Recommendation.builder()
                        .strength(strength)
                        .emotion(detectedEmotion)
                        .motivation(userMotivation)
                        .suggestedResources(String.join("\n", recommendations))
                        .recommendedAt(LocalDateTime.now())
                        .user(user)
                        .build()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("strength", strength);
        response.put("emotion", detectedEmotion);
        response.put("motivation", userMotivation);
        response.put("recommended_resources", recommendations);

        return ResponseEntity.ok(response);
    }
}

