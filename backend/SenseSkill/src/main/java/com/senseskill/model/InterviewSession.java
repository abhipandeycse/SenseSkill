package com.senseskill.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewSession {

    @Id
    private String sessionId;

    @ManyToOne
    private User user;

    @ElementCollection
    private List<String> topics;

    @ElementCollection
    private List<String> questions;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AnswerFeedback> answers;

    private int currentIndex;

    private LocalDateTime startedAt;

    @OneToOne(cascade = CascadeType.ALL)
    private FinalFeedback finalFeedback;
}

