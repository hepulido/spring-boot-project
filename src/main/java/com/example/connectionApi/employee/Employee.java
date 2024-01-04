package com.example.connectionApi.employee;

import com.example.connectionApi.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skills")
    private String skills;

    @Column(name = "status")
    private String status;

    @Column(name = "years_of_experience")
    private int yearsOfExperience;

    @Column(name = "pay_rate")
    private double payRate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}