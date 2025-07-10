package com.example.Health.Assessment.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_disease_history")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientDiseaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diseaseName;
    public int totalScore;
    public String colorCode;
    public String status;
    public String advice;
    public LocalDateTime createdDate;
}
