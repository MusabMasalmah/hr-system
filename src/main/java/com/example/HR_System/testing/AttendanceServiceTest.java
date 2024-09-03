package com.example.HR_System.testing;


import com.example.HR_System.enums.AttendanceType;
import com.example.HR_System.exceptions.DuplicateAttendanceException;
import com.example.HR_System.models.Attendance;
import com.example.HR_System.repositories.AttendanceRepo;
import com.example.HR_System.services.AttendanceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttendanceServiceTest {

    @Mock
    private AttendanceRepo attendanceRepo;

    @InjectMocks
    private AttendanceService attendanceService;

    public AttendanceServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAttendances() {
        // Your test logic here
    }

    @Test
    void testGetAttendanceById_Success() {
        Attendance attendance = new Attendance(1L, 1L,LocalDate.now(), LocalTime.of(9, 0),LocalTime.of(17, 0),AttendanceType.ONSITE);
        when(attendanceRepo.findById(1L)).thenReturn(Optional.of(attendance));

        Attendance result = attendanceService.getAttendanceById(1L);


        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(attendanceRepo, times(1)).findById(1L);
    }

    @Test
    void testGetAttendanceById_NotFound() {
        when(attendanceRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            attendanceService.getAttendanceById(1L);
        });
    }

    @Test
    void testSaveAttendance_Success() {
        Attendance attendance = new Attendance(1L, 1L,LocalDate.now(), LocalTime.of(9, 0),LocalTime.of(17, 0),AttendanceType.ONSITE);
        when(attendanceRepo.findByEmployeeIdAndDate(1L, LocalDate.now())).thenReturn(null);
        when(attendanceRepo.save(attendance)).thenReturn(attendance);

        Attendance result = attendanceService.saveAttendance(attendance);

        assertNotNull(result);
        verify(attendanceRepo, times(1)).save(attendance);
    }

    @Test
    void testSaveAttendance_Duplicate() {
        Attendance attendance = new Attendance(1L, 1L,LocalDate.now(), LocalTime.of(9, 0),LocalTime.of(17, 0),AttendanceType.ONSITE);
        when(attendanceRepo.findByEmployeeIdAndDate(1L, LocalDate.now())).thenReturn(attendance);

        assertThrows(DuplicateAttendanceException.class, () -> {
            attendanceService.saveAttendance(attendance);
        });
    }
}