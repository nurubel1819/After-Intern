package com.example.Health.Assessment.repository;

import com.example.Health.Assessment.entity.PatientHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientHistoryRepo extends JpaRepository<PatientHistory, Long> {
}
