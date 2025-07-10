package com.example.Health.Assessment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;

@Data
public class QuestionsDto {
    @Schema(hidden = true)
    private Long id;
    private String question;
    private String answer;
    private String weight;
}
