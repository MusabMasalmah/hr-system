package com.example.HR_System.models;

import com.example.HR_System.enums.AttendanceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
public class Attendance {

    @Id
    @SequenceGenerator(
            name = "attendance_sequence",
            sequenceName = "attendance_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "attendance_sequence"
    )
    private Long id;

    @NotNull(message = "Employee is required")
    private Long employeeId;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    private LocalTime start_time;

    @NotNull(message = "End time is required")
    private LocalTime end_time;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Attendance type is required")
    private AttendanceType type;

    public Attendance() {
    }

    public Attendance(Long id, Long employeeId, LocalDate date, LocalTime start_time, LocalTime end_time, AttendanceType type) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public AttendanceType getType() {
        return type;
    }

    public void setType(AttendanceType type) {
        this.type = type;
    }
}
