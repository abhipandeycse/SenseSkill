package com.senseskill.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String question;
    private String answer;
    private int confidenceScore;
    private int clarityScore;

    public AnswerFeedback(String question, String answer, int confidence, int clarity) {
        this.question = question;
        this.answer = answer;
        this.confidenceScore = confidence;
        this.clarityScore = clarity;
    }
}
