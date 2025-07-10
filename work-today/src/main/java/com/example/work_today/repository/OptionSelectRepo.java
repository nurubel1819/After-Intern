package com.example.work_today.repository;

import com.example.work_today.entity.OptionSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionSelectRepo extends JpaRepository<OptionSelect,Long> {
    @Query(value = """
        SELECT o.*
        FROM option_select o
        JOIN question q ON o.question_id = q.id
        JOIN disease d ON q.disease_id = d.id
        WHERE d.id = :diseaseId
    """, nativeQuery = true)
    List<OptionSelect> findAllByDiseaseId(@Param("diseaseId") Long diseaseId);
}
