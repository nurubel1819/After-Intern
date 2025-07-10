package com.example.Health.Assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient_history")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_history_id")
    List<PatientDiseaseHistory> patientDiseaseHistories = new ArrayList<>();
}
