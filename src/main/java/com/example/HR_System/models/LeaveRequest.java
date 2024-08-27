package com.example.HR_System.models;

import com.example.HR_System.enums.RequestStatus;
import com.example.HR_System.enums.RequestType;
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
    private LocalDate start_time;
    private LocalDate end_time;
    private RequestStatus status;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Employee hr_employee;
    private String reason;
    private RequestType type;

    public LeaveRequest() {
    }

    public LeaveRequest(Long id, LocalDate start_time, LocalDate end_time, RequestStatus status, Employee employee, Employee hr_employee, String reason, RequestType type) {
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
