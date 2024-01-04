package com.example.connectionApi.user;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String typeOfUser;
    private String skills;
    private String status;
    private int yearsOfExperience;
    private double payRate;
    private String title;
    private String location;
    private String requirements;
    private double salary;
    private String descriptionOfJob;
    private String lengthOfJob;

}
