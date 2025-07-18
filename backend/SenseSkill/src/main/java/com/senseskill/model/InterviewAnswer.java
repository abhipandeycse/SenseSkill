package com.senseskill.model;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class InterviewAnswer {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String question;

	    private String answer;

	    private String feedback;

	    private double score;

	    @ManyToOne
	    @JoinColumn(name = "session_id")
	    private InterviewSession session;
}
