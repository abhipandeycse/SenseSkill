package com.senseskill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senseskill.model.MotivationAnalysis;
import com.senseskill.model.User;


@Repository
public interface MotivationAnalysisRepository extends JpaRepository<MotivationAnalysis, Long> {
    List<MotivationAnalysis> findByUser(User user);
    MotivationAnalysis findTopByUserOrderByAnalyzedAtDesc(User user);
}
