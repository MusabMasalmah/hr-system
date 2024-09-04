package com.example.HR_System.configuration;


import com.example.HR_System.enums.AttendanceType;
import com.example.HR_System.models.Attendance;
import com.example.HR_System.models.Employee;
import com.example.HR_System.repositories.AttendanceRepo;
import com.example.HR_System.repositories.EmployeeRepo;
import com.example.HR_System.enums.Roles;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner loadData(EmployeeRepo employeeRepository, AttendanceRepo attendanceRepo) {
        return args -> {
            Employee employee3 = new Employee();
            employee3.setName("alawia Smith");
            employee3.setEmail("123.smith@example.com");
            employee3.setPicture(null);
            employee3.setCredit(2000.0);
            employee3.setPosition("123 Manager");
            employee3.setPhone_number("0987654321");
            employee3.setAddress("456 Oak Avenue");
            employee3.setMax_paid_leave(20);
            employee3.setRole(Roles.HR);
            employee3.setPassword("securepass4567");

            Employee employee4 = new Employee();
            employee4.setName("anas karakra");
            employee4.setEmail("123.smith@example.com");
            employee4.setPicture(null);
            employee4.setCredit(2000.0);
            employee4.setPosition("123 Manager");
            employee4.setPhone_number("0987654321");
            employee4.setAddress("456 Oak Avenue");
            employee4.setMax_paid_leave(20);
            employee4.setRole(Roles.HR);
            employee4.setPassword("securepass4567");

            Employee employee1 = new Employee();
            employee1.setName("John Doe");
            employee1.setEmail("john.doe@example.com");
            employee1.setPicture(null);
            employee1.setCredit(1500.0);
            employee1.setPosition("Software Engineer");
            employee1.setPhone_number("+1234567890");
            employee1.setAddress("123 Elm Street");
            employee1.setMax_paid_leave(15);
            employee1.setRole(Roles.USER);
            employee1.setPassword("password123");

            Employee employee2 = new Employee();
            employee2.setName("Jane Smith");
            employee2.setEmail("jane.smith@example.com");
            employee2.setPicture(null);
            employee2.setCredit(2000.0);
            employee2.setPosition("Product Manager");
            employee2.setPhone_number("+0987654321");
            employee2.setAddress("456 Oak Avenue");
            employee2.setMax_paid_leave(20);
            employee2.setRole(Roles.ADMIN);
            employee2.setPassword("securepass456");




            // Save employees to the database
            employeeRepository.save(employee3);

            employeeRepository.save(employee1);
            employeeRepository.save(employee2);
            employeeRepository.save(employee4);

            // Create and save attendance records
            Attendance attendance1 = new Attendance();
            attendance1.setDate(LocalDate.now());
            attendance1.setStart_time(LocalTime.of(9, 0));
            attendance1.setEnd_time(LocalTime.of(17, 0));
            attendance1.setType(AttendanceType.ONSITE); // Use AttendanceType enum
            attendance1.setEmployeeId(1l);

            Attendance attendance2 = new Attendance();
            attendance2.setDate(LocalDate.now().minusDays(1));
            attendance2.setStart_time(LocalTime.of(10, 0));
            attendance2.setEnd_time(LocalTime.of(18, 0));
            attendance2.setType(AttendanceType.ONLINE); // Use AttendanceType enum
            attendance2.setEmployeeId(2l);

            attendanceRepo.save(attendance1);
            attendanceRepo.save(attendance2);
        };
    }
}
