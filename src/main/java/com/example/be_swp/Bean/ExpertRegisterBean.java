package com.example.be_swp.Bean;


import lombok.Data;

@Data
public class ExpertRegisterBean {

    private  String username;
    private  String fullName;
    private  String email;
    private  String phone;
    private  String password;
    private  String specialization;
    private  int yearOfExperiences;
    private  String description;
    private String imageBase64;


}
