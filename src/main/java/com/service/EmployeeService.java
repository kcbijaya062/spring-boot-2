package com.service;

import com.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

     Employee authenticate(String email, String pswd);// we make t;his authenticate method

    void save(Employee employee);

    List<Employee> findAllEmployees();

    void deleteEmp(int empid);

    Employee findEmployee(int empid);


    void updateEmployee(Employee employee);

    //List<Employee> findAllEmployees();

    //Employee findEmployee(int empid);

    //Employee findEmployee(int empid);
}    // we make this
