package com.example.work_today.dto;

import lombok.Data;

@Data
public class UserPatientDto {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirmPassword;
    private String address;
    private int age;
}
