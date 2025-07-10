package com.example.work_today.controller;

import com.example.work_today.dto.DiseaseDto;
import com.example.work_today.dto.OptionDto;
import com.example.work_today.dto.QuestionsDto;
import com.example.work_today.dto.UserPatientDto;
import com.example.work_today.entity.Disease;
import com.example.work_today.entity.OptionSelect;
import com.example.work_today.entity.Question;
import com.example.work_today.entity.UserPatient;
import com.example.work_today.mapper.UserPatientMapper;
import com.example.work_today.service.UploadService;
import com.example.work_today.service.UserPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-patient")
public class UserPatientController {
    private final UserPatientService userPatientService;
    private final UserPatientMapper userPatientMapper;
    private final UploadService uploadService;

    @PostMapping("/save-new-user-patient")
    ResponseEntity<?> saveNewUserPatient(@RequestBody UserPatientDto dto){
        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            Map<String, String> error = new HashMap<>();
            error.put("status", "400");
            error.put("error", "Password and confirm password do not match.");
            return ResponseEntity.badRequest().body(error);
        }
        UserPatient userPatient = userPatientMapper.mapToUserPatient(dto);
        userPatient = userPatientService.saveUserPatient(userPatient);
        return ResponseEntity.ok(userPatient);
    }

    @PostMapping("/save-patient-history")
    ResponseEntity<?> savePatientHistory(@RequestParam Long patientId, @RequestBody List<DiseaseDto> dtoAll){
        if(userPatientService.getUserPatientById(patientId) == null) return ResponseEntity.badRequest().body("UserPatient not found");
        UserPatient userPatient = userPatientService.getUserPatientById(patientId);
        if(!userPatient.getDiseases().isEmpty()){
            return ResponseEntity.badRequest().body("UserPatient history already exist");
        }

        List<Disease> allUploadDisease = new ArrayList<>();
        for(DiseaseDto dto : dtoAll){
            Disease disease = new Disease();
            disease.setDisease(dto.getDisease());

            List<Question> questions = new ArrayList<>();

            for (QuestionsDto qdto : dto.getQuestions()) {
                Question question = new Question();
                question.setQuestion(qdto.getQuestion());
                question.setField_input(qdto.getField_input());
                question.setField_type(qdto.getField_type());

                List<OptionSelect> options = new ArrayList<>();
                for (OptionDto optDto : qdto.getOptions()) {
                    OptionSelect option = new OptionSelect();
                    option.setOptionName(optDto.getOption());
                    options.add(option);
                }

                question.setOptions(options);
                questions.add(question);
            }

            disease.setQuestions(questions);

            //Disease savedDisease = uploadService.save_disease(disease);
            allUploadDisease.add(disease);
        }
        userPatient.setDiseases(allUploadDisease);
        userPatient =  userPatientService.saveUserPatient(userPatient);
        return ResponseEntity.ok(allUploadDisease);
    }
    @GetMapping("see-single-patient-history")
    ResponseEntity<?> getPatientHistory(@RequestParam Long patientId){
        if(userPatientService.getUserPatientById(patientId) == null) return ResponseEntity.badRequest().body("UserPatient not found");
        UserPatient userPatient = userPatientService.getUserPatientById(patientId);
        return ResponseEntity.ok(userPatient.getDiseases());
    }
    @GetMapping("see-all-patient-history")
    ResponseEntity<?> getAllPatientHistory(){
        try {
            return ResponseEntity.ok(userPatientService.getAllUserPatient());
        }catch (Exception e){
            System.out.println("Exception from UserPatientService.getAllUserPatient = "+e.getMessage());
            return ResponseEntity.badRequest().body("Exception from UserPatientService.getAllUserPatient = "+e.getMessage());
        }
    }
}
