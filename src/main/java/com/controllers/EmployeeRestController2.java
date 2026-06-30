package com.controllers;

import com.entity.Employee;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/version2")
public class EmployeeRestController2 {
    @Autowired
    EmployeeService employeeService;


    @GetMapping("/employees")//localhost:8080/version2/employees > endPoint
    public ResponseEntity<?> allEmployees() {

        List<Employee> listOfEmployees = employeeService.findAllEmployees();
        //System.out.println(listOfEmployees);
        if(listOfEmployees.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No employee found");
        }


        return ResponseEntity.ok((listOfEmployees));
    }

    @PostMapping("/employees")
    public ResponseEntity<String> doregistration(@RequestBody Employee employee) {
        // System.out.println(employee);
        try{
            employeeService.save(employee);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Registration done successfully");
        }
        catch(Exception exception){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred on the server: " + exception.getMessage());

        }
         // just a message
    }


    @GetMapping("/employees/{empid}")//localhost:8080/version2/employees/6 > endPoint
    public ResponseEntity<?> fetchEmployees(@PathVariable int empid) {

        Employee employee = employeeService.findEmployee(empid);
        //System.out.println(listOfEmployees);
        if (employee == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Employee with ID " + empid + " not found");
        }

        // 3. Return the employee with a 200 OK status
        return ResponseEntity.ok(employee);
        //return employee;
    }


    @DeleteMapping("/employees/{empid}")//localhost:8080/employees/3 > endPoint
    public ResponseEntity<String> deleteEmployees(@PathVariable int empid) {
        try {
            // 1. Using findEmployee method to verify they exist
            Employee employee = employeeService.findEmployee(empid);

            if (employee == null) {
                // If the service returns null, the employee doesn't exist
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Delete failed: Employee with ID " + empid + " does not exist.");
            }

            // 2. Since they exist, safely calling void delete method
            employeeService.deleteEmp(empid);

            return ResponseEntity.ok("Employee with ID " + empid + " deleted successfully.");

        } catch (Exception ex) {
            // 3. Handling any database crashes or connection drops
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + ex.getMessage());
        }
    }

    @PutMapping("/employees/{empid}")//localhost:8080/employees/3 > endPoint
    public ResponseEntity<String> updateEmployees(@PathVariable int empid,@RequestBody Employee employee) {

        try {
            // 2. Explicitly check if the employee exists in the database first
            Employee existingEmployee = employeeService.findEmployee(empid);

            if (existingEmployee == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Update failed: Employee with ID " + empid + " does not exist.");
            }

            // 3. Ensure the object uses the ID passed in the URL path
            employee.setEmpid(empid);

            // 4. Perform the actual update operation
            employeeService.updateEmployee(employee);

            return ResponseEntity.ok("Employee with ID " + empid + " updated successfully.");

        } catch (Exception ex) {
            // 5. Catch genuine server or database connection faults
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred on the server while updating: " + ex.getMessage());
        }
        }
    }


