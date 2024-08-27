package com.example.HR_System.models;

import com.example.HR_System.enums.AttendanceType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
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
    private Employee employee;
    private LocalDate date;
    private LocalDate start_time;
    private LocalDate end_time;
    private AttendanceType type;

    public Attendance() {
    }

    public Attendance(Long id, Employee employee, LocalDate date, LocalDate start_time, LocalDate end_time, AttendanceType type) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.type = type;
    }
}