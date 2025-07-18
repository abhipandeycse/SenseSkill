package com.senseskill.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dominantStrength;

    @Column(length = 2000)
    private String suggestedSkills;

    private LocalDateTime takenAt;

    @ManyToOne
    private User user;
}
