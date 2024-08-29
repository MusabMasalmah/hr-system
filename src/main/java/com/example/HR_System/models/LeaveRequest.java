package com.example.HR_System.models;

import com.example.HR_System.enums.RequestStatus;
import com.example.HR_System.enums.RequestType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leave_request_sequence")
    @SequenceGenerator(name = "leave_request_sequence", sequenceName = "leave_request_sequence", allocationSize = 1)
    private Long id;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    private Long hrEmployeeId;

    @NotNull(message = "Start time is required")
    private LocalDate start_time;

    @NotNull(message = "End time is required")
    private LocalDate end_time;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Request status is required")
    private RequestStatus status;

    @NotNull(message = "Reason is required")
    private String reason;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Request type is required")
    private RequestType type;

    @Lob // for long data
    private String reasonForHr;
}
