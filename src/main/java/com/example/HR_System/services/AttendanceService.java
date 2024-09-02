package com.example.HR_System.services;

import com.example.HR_System.enums.AttendanceType;
import com.example.HR_System.exceptions.DuplicateAttendanceException;
import com.example.HR_System.models.Attendance;
import com.example.HR_System.repositories.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    // Get paginated attendance records
    public Page<Attendance> getAttendances(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return attendanceRepo.findAll(pageable);
    }

    public Page<Attendance> getAttendancesForCurrentMonth(Long employeeId, int page, int size) {
        LocalDate today = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(today);
        LocalDate startDate = currentMonth.atDay(1);
        LocalDate endDate = currentMonth.atEndOfMonth();

        Pageable pageable = PageRequest.of(page, size);
        return attendanceRepo.findAttendancesByEmployeeIdAndMonth(employeeId, startDate, endDate, pageable);
    }



    // Get attendance by ID
    public Attendance getAttendanceById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero");
        }

        return attendanceRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance record not found"));
    }

    // Create or update attendance
    public Attendance saveAttendance(Attendance attendance) {
        if (attendance == null) {
            throw new IllegalArgumentException("Attendance data must not be null");
        }
        attendance.setType(AttendanceType.ONSITE);

        // Validate dates and times
        if (attendance.getDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Attendance date cannot be in the future");
        }

        // Check for existing attendance record for the same employee on the same date
        Attendance existingAttendance = attendanceRepo.findByEmployeeIdAndDate(attendance.getEmployeeId(), attendance.getDate());
        if (existingAttendance != null) {
            throw new DuplicateAttendanceException("Attendance record for this employee on this date already exists");
        }

        if (attendance.getStart_time() == null) {
            attendance.setStart_time(LocalTime.parse("09:00"));
        }
        if (attendance.getEnd_time() == null) {
            attendance.setEnd_time(LocalTime.parse("17:00"));
        }


        return attendanceRepo.save(attendance);
    }


    // Update attendance by ID
    public Attendance updateAttendance(Long id, Attendance attendance) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero");
        }

        // Fetch the existing attendance record
        Attendance existingAttendance = attendanceRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance record not found"));

        // Update fields
        existingAttendance.setDate(attendance.getDate());
        existingAttendance.setStart_time(attendance.getStart_time());
        existingAttendance.setEnd_time(attendance.getEnd_time());
        existingAttendance.setType(attendance.getType());

        // Save and return the updated attendance record
        return attendanceRepo.save(existingAttendance);
    }

    // Delete attendance by ID
    public void deleteAttendance(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero");
        }

        if (!attendanceRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance record not found");
        }

        attendanceRepo.deleteById(id);
    }

    public long calculateTotalWorkingHoursForCurrentMonth(Long employeeId) {
        if (employeeId <= 0) {
            throw new IllegalArgumentException("Employee ID must be greater than zero");
        }

        // Fetch all attendance records for the current month
        List<Attendance> attendances = attendanceRepo.findAttendancesByEmployeeIdForCurrentMonth(employeeId);

        // Calculate the total working hours
        long totalMinutes = attendances.stream()
                .mapToLong(att -> Duration.between(att.getStart_time(), att.getEnd_time()).toMinutes())
                .sum();

        // Convert minutes to hours (as a long)
        return totalMinutes / 60;
    }


    public Page<Attendance> findAllAttendanceByEmployeeId(Long employeeId, int page, int size) {
        if (employeeId <= 0) {
            throw new IllegalArgumentException("Employee ID must be greater than zero");
        }

        Pageable pageable = PageRequest.of(page, size);
        return attendanceRepo.findByEmployeeId(employeeId, pageable);
    }

    public Page<Attendance> getAttendancesForCurrentDay(Pageable pageable) {
        return attendanceRepo.findAttendancesForCurrentDay(pageable);
    }
}
