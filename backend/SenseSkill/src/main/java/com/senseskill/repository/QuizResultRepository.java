package com.senseskill.repository;

import com.senseskill.model.QuizResult;
import com.senseskill.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    List<QuizResult> findByUser(User user);
    QuizResult findTopByUserOrderByTakenAtDesc(User user);
}