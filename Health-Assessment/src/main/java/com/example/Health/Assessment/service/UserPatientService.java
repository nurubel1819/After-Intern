package com.example.Health.Assessment.service;

import com.example.Health.Assessment.entity.Patient;
import com.example.Health.Assessment.repository.PatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPatientService {
    private final PatientRepo patientRepo;

    public Patient savePatient(Patient patient) {
        try {
            return patientRepo.save(patient);
        }catch (Exception e){
            System.out.println("UserPatientService.savePatient failed,Exception:"+e.getMessage());
            return null;
        }
    }
    public Patient getPatientById(Long patientId) {
        try {
            return patientRepo.findById(patientId).get();
            //return patientRepo.findBy(patientId);
        }catch (Exception e){
            System.out.println("UserPatientService.getPatientById failed,Exception:"+e.getMessage());
            return null;
        }
    }
}
