package com.example.HR_System.models;

import com.example.HR_System.enums.RequestStatus;
import com.example.HR_System.enums.RequestType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
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
    private LocalDate startTime;
    private LocalDate endTime;
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // The employee requesting leave

    @ManyToOne
    @JoinColumn(name = "hr_employee_id") // Different column name
    @JsonIgnore
    private Employee hrEmployee;

    private String reason;

    @Enumerated(EnumType.STRING)
    private RequestType type;

    public LeaveRequest() {
    }


}
