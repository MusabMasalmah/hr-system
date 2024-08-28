package com.example.HR_System.dto;

import com.example.HR_System.enums.AttendanceType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendanceDto {

    private Long id;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    @FutureOrPresent(message = "Start time cannot be in the past")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    @FutureOrPresent(message = "End time cannot be in the past")
    private LocalTime endTime;

    @NotNull(message = "Attendance type is required")
    private AttendanceType type;

    public AttendanceDto() {
    }

    public AttendanceDto(Long id, @NotNull(message = "Employee ID is required") Long employeeId, @NotNull(message = "Date is required") LocalDate date, @NotNull(message = "Start time is required") LocalTime startTime, @NotNull(message = "End time is required") LocalTime endTime, @NotNull(message = "Attendance type is required") AttendanceType type) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }
}
