package com.senseskill.repository;

import com.senseskill.model.LearningProgress;
import com.senseskill.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LearningProgressRepository extends JpaRepository<LearningProgress, Long> {
    List<LearningProgress> findByUser(User user);
    
    @Query(
    		  value = "SELECT lt.name, COUNT(lp.id) AS total " +
    		          "FROM learning_progress lp " +
    		          "JOIN user u ON lp.user_id = u.id " +
    		          "JOIN learning_topic lt ON lp.topic_id = lt.id " +
    		          "WHERE u.strength = :strength " +
    		          "GROUP BY lt.name " +
    		          "ORDER BY total DESC",
    		  nativeQuery = true
    		)
    		List<Object[]> findPopularTopicsByStrength(@Param("strength") String strength);
}
