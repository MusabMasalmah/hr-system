package com.example.HR_System.controllers;


import com.example.HR_System.models.Employee;
import com.example.HR_System.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/api/")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/v0/employees")
    public ResponseEntity<List<Employee>> employees() {
        List<Employee> employees = employeeService.getEmployees()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/v0/employees")
    public ResponseEntity<Employee> postEmployee(@Valid @RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.postEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

    @PutMapping("/v0/employees/update")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
        try {
            employeeService.updateEmployee(employee);
            return ResponseEntity.ok("Employee updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/v0/employees/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @GetMapping("/v0/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }


}
