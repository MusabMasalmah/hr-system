package com.example.HR_System.models;

import com.example.HR_System.enums.RequestStatus;
import com.example.HR_System.enums.RequestType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class LeaveRequest {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leave_request_sequence")
    @SequenceGenerator(name = "leave_request_sequence", sequenceName = "leave_request_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "hr_employee_id", nullable = false)
    private Employee hrEmployee;

    private LocalDate startTime;
    private LocalDate endTime;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String reason;

    @Enumerated(EnumType.STRING)
    private RequestType type;

}
