package com.example.Health.Assessment.dto;

import lombok.Data;

@Data
public class StatusDto {
    public String diseaseName;
    public int totalScore;
    public String colorCode;
    public String status;
    public String advice;
}
