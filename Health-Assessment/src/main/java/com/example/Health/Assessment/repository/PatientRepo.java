package com.example.Health.Assessment.repository;

import com.example.Health.Assessment.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Long> {
}
