package com.senseskill.repository;

import com.senseskill.model.LearningTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearningTopicRepository extends JpaRepository<LearningTopic, Long> {
    Optional<LearningTopic> findByName(String name);
}
