package com.example.HR_System.models;


import com.example.HR_System.enums.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long id;

    private String name;
    private String email;
    private Byte picture;
    private Double credit;
    private String position;
    private String phone_number;
    private String address;
    private Integer max_paid_leave;
    private Roles role;
}
