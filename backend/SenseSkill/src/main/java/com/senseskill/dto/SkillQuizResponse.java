package com.senseskill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillQuizResponse {
    private Long questionId;
    private String selectedOption;
}

