package com.service;


import com.dao.EmployeeRepository;
import com.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService{
@Autowired
EmployeeRepository employeeRepository ;   // sservice layer calls Dao layer

    @Override
    public Employee authenticate(String email, String pswd) {
       Employee employee;
        employee = employeeRepository.findByEmpemailAndEmppass(email,pswd);
        return employee;
    }

    @Override
    public void save(Employee employee) {
    employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
       List<Employee> employeelist = employeeRepository.findAll();
        return employeelist;
    }

    @Override
    public void deleteEmp(int eid) {
        employeeRepository.deleteById(eid);
    }

    @Override
    public Employee findEmployee(int empid) {
        Optional<Employee> optional = employeeRepository.findById(empid);
        if (optional.isEmpty()) {

            return null;
        } else{
            return optional.get();
        }


    }


    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);

    }

//    @Override
//    public List<Employee> findAllEmployees() {
//        return List.of();
//    }



}
