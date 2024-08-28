package com.example.HR_System.repositories;

import com.example.HR_System.models.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, Long> {
    // Custom query methods (if needed) can be defined here
}
