package com.example.Health.Assessment.controller;

import com.example.Health.Assessment.dto.DiseaseDto;
import com.example.Health.Assessment.dto.QuestionsDto;
import com.example.Health.Assessment.dto.UserPatientDto;
import com.example.Health.Assessment.entity.Disease;
import com.example.Health.Assessment.entity.Patient;
import com.example.Health.Assessment.entity.Question;
import com.example.Health.Assessment.service.UserPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class UserPatientController {
    private final UserPatientService userPatientService;

    @PostMapping("/save-patient")
    ResponseEntity<?> savePatient(@RequestBody UserPatientDto dto){
        Patient patient = new Patient();
        patient.setPatientName(dto.getPatientName());
        patient.setPatientAge(dto.getPatientAge());

        patient =  userPatientService.savePatient(patient);
        if(patient == null){
            return ResponseEntity.badRequest().body("Patient not saved");
        }
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/save-patient-history")
    ResponseEntity<?> savePatientHistory(@RequestParam Long patientId, @RequestBody List<DiseaseDto> dtoAll){
        if(userPatientService.getPatientById(patientId) == null) return ResponseEntity.badRequest().body("UserPatient not found");
        Patient userPatient = userPatientService.getPatientById(patientId);
        if(!userPatient.getDiseases().isEmpty()){
            return ResponseEntity.badRequest().body("UserPatient history already exist");
        }

        List<Disease> allUploadDisease = new ArrayList<>();
        for(DiseaseDto dto : dtoAll){
            Disease disease = new Disease();
            disease.setDiseaseName(dto.getDisease());

            List<Question> questions = new ArrayList<>();

            for (QuestionsDto qdto : dto.getQuestions()) {
                Question question = new Question();
                question.setQuestion(qdto.getQuestion());
                if(qdto.getAnswer().equals("yes")){
                    question.setWeight(1);
                }else question.setWeight(0);
                question.setAnswer(qdto.getAnswer());

                questions.add(question);
            }

            disease.setQuestions(questions);

            //Disease savedDisease = uploadService.save_disease(disease);
            allUploadDisease.add(disease);
        }
        userPatient.setDiseases(allUploadDisease);
        userPatient =  userPatientService.savePatient(userPatient);
        return ResponseEntity.ok(allUploadDisease);
    }

}
