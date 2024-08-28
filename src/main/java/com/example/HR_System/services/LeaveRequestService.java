package com.example.HR_System.services;

import com.example.HR_System.models.LeaveRequest;
import com.example.HR_System.repositories.LeaveRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepo leaveRequestRepo;

    // Create a new leave request
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestRepo.save(leaveRequest);
    }

    // Get all leave requests
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepo.findAll();
    }

    // Get a leave request by ID
    public Optional<LeaveRequest> getLeaveRequestById(Long id) {
        return leaveRequestRepo.findById(id);
    }

    // Update an existing leave request
    public LeaveRequest updateLeaveRequest(Long id, LeaveRequest updatedLeaveRequest) {
        Optional<LeaveRequest> leaveRequestOptional = leaveRequestRepo.findById(id);
        if (leaveRequestOptional.isPresent()) {
            LeaveRequest leaveRequest = leaveRequestOptional.get();
            leaveRequest.setEmployee(updatedLeaveRequest.getEmployee());
            leaveRequest.setHrEmployee(updatedLeaveRequest.getHrEmployee());
            leaveRequest.setStartTime(updatedLeaveRequest.getStartTime());
            leaveRequest.setEndTime(updatedLeaveRequest.getEndTime());
            leaveRequest.setStatus(updatedLeaveRequest.getStatus());
            leaveRequest.setReason(updatedLeaveRequest.getReason());
            leaveRequest.setType(updatedLeaveRequest.getType());
            return leaveRequestRepo.save(leaveRequest);
        } else {
            throw new RuntimeException("Leave request not found");
        }
    }

    // Delete a leave request by ID
    public void deleteLeaveRequest(Long id) {
        leaveRequestRepo.deleteById(id);
    }
}
