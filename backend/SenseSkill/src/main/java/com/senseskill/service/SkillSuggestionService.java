package com.senseskill.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillSuggestionService {

    private final Map<String, List<String>> suggestions = Map.of(
        "logical", List.of("DSA", "System Design", "Problem Solving"),
        "verbal", List.of("Public Speaking", "Writing", "Interviewing"),
        "creative", List.of("UI/UX", "Illustration", "Storyboarding"),
        "technical", List.of("Backend Dev", "DevOps", "ML/AI")
    );

    public List<String> getSuggestions(String strength) {
        return suggestions.getOrDefault(strength, List.of("Explore more"));
    }
}
