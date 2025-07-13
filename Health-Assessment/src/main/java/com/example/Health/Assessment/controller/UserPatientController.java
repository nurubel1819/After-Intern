package com.example.Health.Assessment.controller;

import com.example.Health.Assessment.dto.DiseaseDto;
import com.example.Health.Assessment.dto.QuestionsDto;
import com.example.Health.Assessment.dto.StatusDto;
import com.example.Health.Assessment.dto.UserPatientDto;
import com.example.Health.Assessment.entity.*;
import com.example.Health.Assessment.helper.CalculateStatus;
import com.example.Health.Assessment.service.UserPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PutMapping("/update-patient-survey")
    ResponseEntity<?> updatePatientSurvey(@RequestParam Long patientId, @RequestBody List<DiseaseDto> dtoAll){
        Patient userPatient = userPatientService.getPatientById(patientId);
        if(userPatient == null) return ResponseEntity.badRequest().body("UserPatient not found");

        if(!userPatient.getDiseases().isEmpty()){
            PatientHistory patientHistory = new PatientHistory();
            List<Disease> allDisease = userPatient.getDiseases();
            for(int i=0;i<dtoAll.size();i++){
                PatientDiseaseHistory patientDiseaseHistory = new PatientDiseaseHistory();
                allDisease.get(i).setDiseaseName(dtoAll.get(i).getDisease());

                List<Question> questions = allDisease.get(i).getQuestions();
                List<QuestionsDto> qdto = dtoAll.get(i).getQuestions();
                int totalScore = 0;
                for(int j=0;j<qdto.size();j++){
                    questions.get(j).setQuestion(qdto.get(j).getQuestion());
                    if(qdto.get(j).getAnswer().equals("yes")){
                        totalScore++;
                        questions.get(j).setWeight(1);
                    }else questions.get(j).setWeight(0);
                    questions.get(j).setAnswer(qdto.get(j).getAnswer());
                }
                allDisease.get(i).setQuestions(questions);
                StatusDto statusDto = CalculateStatus.diseaseStatus(allDisease.get(i).getDiseaseName(), totalScore);
                if(statusDto!=null){
                    allDisease.get(i).setTotalScore(totalScore);
                    allDisease.get(i).setStatus(statusDto.getStatus());
                    allDisease.get(i).setColorCode(statusDto.getColorCode());
                    allDisease.get(i).setAdvice(statusDto.getAdvice());

                    // history table
                    patientDiseaseHistory.setDiseaseName(allDisease.get(i).getDiseaseName());
                    patientDiseaseHistory.setTotalScore(totalScore);
                    patientDiseaseHistory.setStatus(statusDto.getStatus());
                    patientDiseaseHistory.setColorCode(statusDto.getColorCode());
                    patientDiseaseHistory.setAdvice(statusDto.getAdvice());
                    patientDiseaseHistory.setCreatedDate(LocalDateTime.now());
                }
                patientHistory.setPatientName(userPatient.getPatientName());
                patientHistory.getPatientDiseaseHistories().add(patientDiseaseHistory);
            }
            userPatient.setDiseases(allDisease);
            userPatient.getPatientHistory().add(patientHistory);
            userPatient =  userPatientService.savePatient(userPatient);
            return ResponseEntity.ok(allDisease);
        }
        else return ResponseEntity.badRequest().body("User disease is Empty");
    }

    @PostMapping("/save-patient-survey")
    ResponseEntity<?> savePatientHistory(@RequestParam Long patientId, @RequestBody List<DiseaseDto> dtoAll){
        Patient userPatient = userPatientService.getPatientById(patientId);
        if(userPatient == null) return ResponseEntity.badRequest().body("UserPatient not found");

        PatientHistory patientHistory = new PatientHistory();

        List<Disease> allUploadDisease = new ArrayList<>();
        for(DiseaseDto dto : dtoAll){
            PatientDiseaseHistory patientDiseaseHistory = new PatientDiseaseHistory();
            Disease disease = new Disease();
            disease.setDiseaseName(dto.getDisease());

            List<Question> questions = new ArrayList<>();

            int totalScore = 0;
            for (QuestionsDto qdto : dto.getQuestions()) {
                Question question = new Question();
                question.setQuestion(qdto.getQuestion());
                if(qdto.getAnswer().equals("yes")){
                    totalScore++;
                    question.setWeight(1);
                }else question.setWeight(0);
                question.setAnswer(qdto.getAnswer());

                questions.add(question);
            }

            disease.setQuestions(questions);
            StatusDto statusDto = CalculateStatus.diseaseStatus(disease.getDiseaseName(), totalScore);
            if(statusDto!=null){
                disease.setTotalScore(totalScore);
                disease.setStatus(statusDto.getStatus());
                disease.setColorCode(statusDto.getColorCode());
                disease.setAdvice(statusDto.getAdvice());

                // history table
                patientDiseaseHistory.setDiseaseName(disease.getDiseaseName());
                patientDiseaseHistory.setTotalScore(totalScore);
                patientDiseaseHistory.setStatus(statusDto.getStatus());
                patientDiseaseHistory.setColorCode(statusDto.getColorCode());
                patientDiseaseHistory.setAdvice(statusDto.getAdvice());
                patientDiseaseHistory.setCreatedDate(LocalDateTime.now());
            }
            allUploadDisease.add(disease);
            patientHistory.setPatientName(userPatient.getPatientName());
            patientHistory.getPatientDiseaseHistories().add(patientDiseaseHistory);
        }
        userPatient.getDiseases().addAll(allUploadDisease);
        userPatient.getPatientHistory().add(patientHistory);
        userPatient =  userPatientService.savePatient(userPatient);
        return ResponseEntity.ok(allUploadDisease);
    }
    @GetMapping("/get-single-patient-history")
    ResponseEntity<?> getSinglePatientHistory(@RequestParam Long patientId){
        Patient patient = userPatientService.getPatientById(patientId);
        if(patient == null) return ResponseEntity.badRequest().body("Patient not found");
        return ResponseEntity.ok(patient.getPatientHistory());
    }
    @GetMapping("/v2/get-single-patient-history")
    ResponseEntity<?> getSinglePatientHistoryVersion2(@RequestParam Long patientId){
        Patient patient = userPatientService.getPatientById(patientId);
        if(patient == null) return ResponseEntity.badRequest().body("Patient not found");
        List<Disease> patientAllDisease = patient.getDiseases();
        List<DiseaseDto> allDto = new ArrayList<>();
        for(Disease disease : patientAllDisease){
            DiseaseDto dto = new DiseaseDto();
            dto.setDisease(disease.getDiseaseName());
            dto.setStatus(disease.getStatus());
            dto.setTotalScore(disease.getTotalScore());
            dto.setColorCode(disease.getColorCode());
            dto.setAdvice(disease.getAdvice());
            allDto.add(dto);
        }
        return ResponseEntity.ok(allDto);
    }

}
