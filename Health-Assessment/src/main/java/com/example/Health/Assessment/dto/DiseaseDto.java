package com.example.Health.Assessment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;

@Data
public class DiseaseDto {
    @Schema(hidden = true)
    private Long id;
    private String disease;
    private ArrayList<QuestionsDto> questions;
    public int totalScore;
    public String colorCode;
    public String status;
    public String advice;
}