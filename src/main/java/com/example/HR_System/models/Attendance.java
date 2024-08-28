package com.example.HR_System.models;

import com.example.HR_System.enums.AttendanceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Setter
@Getter
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

    @ManyToOne
    @NotNull(message = "Employee is required")
    private Employee employee;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    @FutureOrPresent(message = "Start time cannot be in the past")
    private LocalTime start_time;

    @NotNull(message = "End time is required")
    @FutureOrPresent(message = "End time cannot be in the past")
    private LocalTime end_time;

    @NotNull(message = "Attendance type is required")
    private AttendanceType type;

    public Attendance() {
    }

    public Attendance(Long id, Employee employee, LocalDate date, LocalTime start_time, LocalTime end_time, AttendanceType type) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.type = type;
    }

}
