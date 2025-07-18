package com.senseskill.repository;

import com.senseskill.model.SkillQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillQuestionRepository extends JpaRepository<SkillQuestion, Long> {}
