package com.example.HR_System.services;

import com.example.HR_System.dto.EmployeeDto;
import com.example.HR_System.models.Employee;
import com.example.HR_System.repositories.EmployeeRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.HR_System.mapper.EmployeeMapper;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    public EmployeeService(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
    }


    //Get employees
    public Optional<List<Employee>> getEmployees(){
        return Optional.of(employeeRepo.findAll());
    }



    //checks if employee exists by address and name if they exist together
    public Employee postEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Invalid employee data: employee ");
        }
        boolean employeeOptionalExists = employeeRepo.existsByNameAndAddress(employee.getName(),employee.getAddress());
        if(employeeOptionalExists){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee with the same name and address already exists");

            }
        return employeeRepo.save(employee);
    }


    //Update employee by id
    public void updateEmployee(Employee employee) {
        if (employee == null || employee.getId() == null) {
            throw new IllegalArgumentException("Invalid employee data: employee or ID is null");
        }

        Employee existingEmployee = employeeRepo.findById(employee.getId())
                .orElseThrow(() -> new IllegalStateException("Employee doesn't exist"));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPicture(employee.getPicture());
        existingEmployee.setCredit(employee.getCredit());
        existingEmployee.setPosition(employee.getPosition());
        existingEmployee.setPhone_number(employee.getPhone_number());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setMax_paid_leave(employee.getMax_paid_leave());
        existingEmployee.setRole(employee.getRole());
        existingEmployee.setPassword(employee.getPassword());

        employeeRepo.save(existingEmployee);

    }

    // delete empployee
    public void deleteEmployee(Long employeeId){
        if (employeeId <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero");
        }

            Employee existingEmployee = employeeRepo.findById(employeeId)
                    .orElseThrow(() -> new IllegalStateException("Employee doesn't exist"));
            employeeRepo.deleteById(employeeId);
        }


    // get user by id
    public Employee getById(Long employeeId){
        if (employeeId <= 0 ) {
            throw new IllegalArgumentException("ID must be greater than zero");
        }

        return employeeRepo.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException("Employee doesn't exist"));
    }


    public Page<EmployeeDto> getAllEmployeesPageable(Pageable pageable) {
        Page<Employee> employeePage = employeeRepo.findAll(pageable);
        return employeePage.map(employeeMapper::toDto);
    }

    }


