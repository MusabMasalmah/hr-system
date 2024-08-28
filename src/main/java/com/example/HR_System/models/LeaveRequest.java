package com.example.HR_System.models;

import com.example.HR_System.enums.RequestStatus;
import com.example.HR_System.enums.RequestType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class LeaveRequest {
    @Id
    @SequenceGenerator(
            name = "LeaveRequest_sequence",
            sequenceName = "LeaveRequest_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LeaveRequest_sequence"
    )
    private Long id;
    private LocalDate start_time;
    private LocalDate end_time;
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // The employee requesting leave

    @ManyToOne
    @JoinColumn(name = "hr_employee_id") // Different column name
    @JsonIgnore
    private Employee hrEmployee;

    private String reason;
    private RequestType type;



}
