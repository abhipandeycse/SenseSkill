package com.senseskill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senseskill.model.InterviewAnswer;
import com.senseskill.model.InterviewSession;

@Repository
public interface InterviewAnswerRepository extends JpaRepository<InterviewAnswer, Long> {
    List<InterviewAnswer> findBySession(InterviewSession session);
}