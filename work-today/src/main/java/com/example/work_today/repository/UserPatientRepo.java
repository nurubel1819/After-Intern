package com.example.work_today.repository;

import com.example.work_today.entity.UserPatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPatientRepo extends JpaRepository<UserPatient,Long> {

}
