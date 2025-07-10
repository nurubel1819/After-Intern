package com.example.work_today.mapper;

import com.example.work_today.dto.UserPatientDto;
import com.example.work_today.entity.UserPatient;
import org.springframework.stereotype.Component;

@Component
public class UserPatientMapper {
    public UserPatient mapToUserPatient(UserPatientDto dto){
        UserPatient userPatient = new UserPatient();
        userPatient.setName(dto.getName());
        userPatient.setPhoneNumber(dto.getPhoneNumber());
        userPatient.setEmail(dto.getEmail());
        userPatient.setPassword(dto.getPassword());
        userPatient.setAddress(dto.getAddress());
        userPatient.setAge(dto.getAge());

        return userPatient;
    }
}
