package com.senseskill.repository;

import com.senseskill.model.EmotionAnalysis;
import com.senseskill.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmotionAnalysisRepository extends JpaRepository<EmotionAnalysis, Long> {
    List<EmotionAnalysis> findByUser(User user);
    EmotionAnalysis findTopByUserOrderByAnalyzedAtDesc(User user);
}
