package com.example.HR_System.services;

import com.example.HR_System.models.LeaveRequest;
import com.example.HR_System.repositories.LeaveRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestServices {

    private final LeaveRequestRepo leaveRequestRepo;

    @Autowired
    public LeaveRequestServices(LeaveRequestRepo leaveRequestRepo) {
        this.leaveRequestRepo = leaveRequestRepo;
    }

    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        // You may add validation for the ID fields if needed
        return leaveRequestRepo.save(leaveRequest);
    }

    public LeaveRequest updateLeaveRequest(Long id, LeaveRequest leaveRequest) {
        if (!leaveRequestRepo.existsById(id)) {
            throw new RuntimeException("LeaveRequest not found with id " + id);
        }
        leaveRequest.setId(id);
        return leaveRequestRepo.save(leaveRequest);
    }

    public void deleteLeaveRequest(Long id) {
        if (!leaveRequestRepo.existsById(id)) {
            throw new RuntimeException("LeaveRequest not found with id " + id);
        }
        leaveRequestRepo.deleteById(id);
    }

    public Optional<LeaveRequest> getLeaveRequestById(Long id) {
        return leaveRequestRepo.findById(id);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepo.findAll();
    }
}
