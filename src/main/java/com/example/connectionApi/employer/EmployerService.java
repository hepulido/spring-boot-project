package com.example.connectionApi.employer;

import com.example.connectionApi.employee.Employee;
import com.example.connectionApi.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {
    private final EmployeeRepository employeeRepository;
    private final EmployerRepository employerRepository;


    @Autowired
    public EmployerService(EmployeeRepository employeeRepository, EmployerRepository employerRepository) {
        this.employeeRepository = employeeRepository;
        this.employerRepository = employerRepository;
    }


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee>  getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }
    public Optional<Employer> getEmployerByUserId(Long userId){
        return employerRepository.findByUserId(userId);
    }

}

