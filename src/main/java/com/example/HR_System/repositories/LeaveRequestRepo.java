package com.example.HR_System.repositories;

import com.example.HR_System.models.LeaveRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, Long> {

    // retrieves a list of all leave requests associated with a specific employee
    List<LeaveRequest> findByEmployeeId(Long employeeId);

    // retrieves a list of all leave requests managed by a specific HR employee
    List<LeaveRequest> findByHrEmployeeId(Long hrEmployeeId);

    // retrieves a paginated list of leave requests associated with a specific employee
    Page<LeaveRequest> findByEmployeeId(Long employeeId, Pageable pageable);

    // retrieves a paginated list of leave requests managed by a specific HR employee
    Page<LeaveRequest> findByHrEmployeeId(Long hrEmployeeId, Pageable pageable);

}
