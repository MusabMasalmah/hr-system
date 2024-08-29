package com.example.HR_System.controllers;

import com.example.HR_System.models.Attendance;
import com.example.HR_System.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Get paginated attendance records
    @GetMapping("/V0/get_AllAttendances")
    public ResponseEntity<Page<Attendance>> getAttendances(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(attendanceService.getAttendances(page, size));
    }

    // Get attendance by ID
    @GetMapping("/V0/get_Attendance/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
        Attendance attendance = attendanceService.getAttendanceById(id);
        return ResponseEntity.ok(attendance);
    }

    // Create or update attendance
    @PostMapping("/V0/save_Attendance")
    public ResponseEntity<Attendance> saveAttendance(@RequestBody Attendance attendance) {
        Attendance savedAttendance = attendanceService.saveAttendance(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
    }

    // Update attendance by ID
    @PutMapping("/V0/update_Attendance/{id}/{employeeId}")
    public ResponseEntity<Attendance> updateAttendance(
            @PathVariable Long id,
            @Valid @RequestBody Attendance attendance) {
        Attendance updatedAttendance = attendanceService.updateAttendance(id, attendance);
        return ResponseEntity.ok(updatedAttendance);
    }

    // Delete attendance by ID
    @DeleteMapping("/V0/delete_Attendance/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/V0/get_AttendancesByEmployeeId/{employeeId}")
    public ResponseEntity<?> getAttendancesByEmployeeId(
            @PathVariable Long employeeId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(attendanceService.findAllAttendanceByEmployeeId(employeeId, page, size));
    }

    @GetMapping("/V0/get_TotalWorkingHoursForCurrentMonth/{employeeId}")
    public ResponseEntity<?> getTotalWorkingHoursForCurrentMonth(@PathVariable Long employeeId) {
        long totalHours = attendanceService.calculateTotalWorkingHoursForCurrentMonth(employeeId);
        return ResponseEntity.ok(totalHours);
    }

    @GetMapping("/V0/get_TodayAttendances")
    public ResponseEntity<Page<Attendance>> getAttendancesForCurrentDay(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<Attendance> attendances = attendanceService.getAttendancesForCurrentDay(pageable);
        return ResponseEntity.ok(attendances);
    }
}
