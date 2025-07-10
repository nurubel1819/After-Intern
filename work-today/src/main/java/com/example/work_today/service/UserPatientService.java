package com.example.work_today.service;

import com.example.work_today.entity.UserPatient;
import com.example.work_today.repository.UserPatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPatientService {

    private final UserPatientRepo userPatientRepo;

     public UserPatient saveUserPatient(UserPatient userPatient){
        try {
            return userPatientRepo.save(userPatient);
        }catch (Exception e){
            System.out.println("Exception from UserPatientService.saveUserPatient = "+e.getMessage());
            return null;
        }
    }
    public UserPatient getUserPatientById(Long id){
         try {
             return userPatientRepo.findById(id).get();
         }catch (Exception e){
             System.out.println("Exception from UserPatientService.getUserPatientById = "+e.getMessage());
             return null;
         }
    }
    public List<UserPatient> getAllUserPatient(){
         try {
            return userPatientRepo.findAll();
         }catch (Exception e){
             System.out.println("Exception from UserPatientService.getAllUserPatient = "+e.getMessage());
             return null;
         }
    }
}
