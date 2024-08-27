package com.example.HR_System.Models;

import com.example.HR_System.Enums.Request_Status;
import com.example.HR_System.Enums.Request_Type;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class LeaveRequest {
    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long id;
    private LocalDate start_time;
    private LocalDate end_time;
    private Request_Status status;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Employee hr_employee;
    private String reason;
    private Request_Type type;

    public LeaveRequest() {
    }

    public LeaveRequest(Long id, LocalDate start_time, LocalDate end_time, Request_Status status, Employee employee, Employee hr_employee, String reason, Request_Type type) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.status = status;
        this.employee = employee;
        this.hr_employee = hr_employee;
        this.reason = reason;
        this.type = type;
    }
}
