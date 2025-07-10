package com.example.work_today.repository;

import com.example.work_today.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiseaseRepo extends JpaRepository<Disease,Long> {

    Disease findByDisease(String disease);
}
