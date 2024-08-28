package com.example.HR_System.repositories;

import com.example.HR_System.models.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {
    // Add this method to find attendance records by employeeId
    Page<Attendance> findByEmployeeId(Long employeeId, Pageable pageable);

    @Query("SELECT a FROM Attendance a WHERE a.employeeId = :employeeId AND MONTH(a.date) = MONTH(CURRENT_DATE) AND YEAR(a.date) = YEAR(CURRENT_DATE)")
    List<Attendance> findAttendancesByEmployeeIdForCurrentMonth(@Param("employeeId") Long employeeId);

    @Query("SELECT a FROM Attendance a WHERE a.date = CURRENT_DATE")
    Page<Attendance> findAttendancesForCurrentDay(Pageable pageable);
}
