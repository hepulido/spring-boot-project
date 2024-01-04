package com.example.connectionApi.employee;

import com.example.connectionApi.employer.Employer;
import com.example.connectionApi.employer.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployerRepository employerRepository;
    private final EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeService(EmployerRepository employerRepository, EmployeeRepository employeeRepository) {
        this.employerRepository = employerRepository;

        this.employeeRepository = employeeRepository;
    }


    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    public Optional<Employer>  getEmployerById(Long employerId) {
        return employerRepository.findById(employerId);
    }
    public Optional<Employee> getEmployeeByUserId(Long userId){
        return employeeRepository.findByUserId(userId);
    }
}
