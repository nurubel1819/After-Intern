package com.example.Health.Assessment.repository;

import com.example.Health.Assessment.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepo extends JpaRepository<Disease,Long> {
}
