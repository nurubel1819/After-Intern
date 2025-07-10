package com.example.Health.Assessment.repository;

import com.example.Health.Assessment.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question,Long> {
}
