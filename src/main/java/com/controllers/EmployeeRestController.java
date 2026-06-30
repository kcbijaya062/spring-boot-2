package com.controllers;

import com.entity.Employee;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@RestController
@RequestMapping("/version1")
public class EmployeeRestController {
    @Autowired
    EmployeeService employeeService;


    @GetMapping("/employees")//localhost:8080/version1/employees > endPoint
    public List<Employee> allEmployees() {

        List<Employee> listOfEmployees = employeeService.findAllEmployees();
        //System.out.println(listOfEmployees);

        return listOfEmployees;
    }

    @PostMapping("/employees") //localhost:8080/version1/employees
    public String doregistration(@RequestBody Employee employee) {
        // System.out.println(employee);
        employeeService.save(employee);
        return "registration done successfully";  // just a message
    }


    @GetMapping("/employees/{empid}")//localhost:8080/version1/employees/6 > endPoint
    public Employee fetchEmployees(@PathVariable int empid) {

        Employee employee = employeeService.findEmployee(empid);
        //System.out.println(listOfEmployees);

        return employee;
    }


    @DeleteMapping("/employees/{empid}")//localhost:8080/version1/employees/3 > endPoint
    public String deleteEmployees(@PathVariable int empid) {

        employeeService.deleteEmp(empid);

        return " employee deleted successfully";
    }

    @PutMapping("/employees/{empid}")//localhost:8080/version1/employees/3 > endPoint
    public String updateEmployees(@PathVariable int empid,@RequestBody Employee employee) {

        employee.setEmpid(empid);
        try{
        employeeService.updateEmployee(employee);
        return " employee updated successfully";
    }
        catch(Exception e){
            return" update failed : may be due to invalid empid";
        }
    }
}

