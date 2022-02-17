package com.example.employee.controllers;

import com.example.employee.models.Employee;
import com.example.employee.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public Employee createEmloyee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        boolean deleted;
        deleted = employeeService.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();

        if (deleted) {
            response.put("deleted ", deleted);
            return ResponseEntity.ok(response);
        }
        else {
            response.put("There is no employee with " + id + " number!", deleted);
            return null;
        }

    }
}
