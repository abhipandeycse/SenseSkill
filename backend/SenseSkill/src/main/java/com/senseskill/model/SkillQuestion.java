package com.senseskill.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String category; // logical, creative, technical, verbal
}
