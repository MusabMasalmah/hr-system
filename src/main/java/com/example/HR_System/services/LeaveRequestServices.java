package com.example.HR_System.services;
import com.example.HR_System.enums.RequestStatus;
import com.example.HR_System.models.LeaveRequest;
import com.example.HR_System.repositories.LeaveRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestServices implements LeaveRequestServiceInterface {

    private final LeaveRequestRepo leaveRequestRepo;

    @Autowired
    public LeaveRequestServices(LeaveRequestRepo leaveRequestRepo) {
        this.leaveRequestRepo = leaveRequestRepo;
    }

    // add leave request
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        System.out.println("Received LeaveRequest: " + leaveRequest);
        leaveRequest.setStatus(RequestStatus.PENDING);// employee can't change the status and it's by default pending
        leaveRequest.setReasonForHr(null); // employee can't add reason of rejected or accepted
        LeaveRequest savedLeaveRequest = leaveRequestRepo.save(leaveRequest);
        System.out.println("Saved LeaveRequest: " + savedLeaveRequest);
        return savedLeaveRequest;
    }

    // update by employee (can update all fields except status and reasonForHr)
    public LeaveRequest updateLeaveRequestByEmployee(Long id, LeaveRequest leaveRequest) {
        if (!leaveRequestRepo.existsById(id)) {
            throw new RuntimeException("LeaveRequest not found with id " + id);
        }
        LeaveRequest existingLeaveRequest = leaveRequestRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("LeaveRequest not found with id " + id));

        existingLeaveRequest.setEmployeeId(leaveRequest.getEmployeeId());
        existingLeaveRequest.setHrEmployeeId(leaveRequest.getHrEmployeeId());
        existingLeaveRequest.setStart_time(leaveRequest.getStart_time());
        existingLeaveRequest.setEnd_time(leaveRequest.getEnd_time());
        existingLeaveRequest.setReason(leaveRequest.getReason());
        existingLeaveRequest.setType(leaveRequest.getType());
        existingLeaveRequest.setStatus(RequestStatus.PENDING); // ensure status remains PENDING

        return leaveRequestRepo.save(existingLeaveRequest);
    }

    // update by HR (can update only the status and reasonForHr)
    @Transactional // Add this annotation
    public LeaveRequest updateLeaveRequestByHR(Long id, LeaveRequest leaveRequest) {
        Optional<LeaveRequest> existingLeaveRequest = leaveRequestRepo.findById(id);
        if (!existingLeaveRequest.isPresent()) {
            throw new RuntimeException("LeaveRequest not found with id " + id);
        }
        LeaveRequest existingRequest = existingLeaveRequest.get();
        // update only the status and reasonForHr
        existingRequest.setStatus(leaveRequest.getStatus());
        existingRequest.setReasonForHr(leaveRequest.getReasonForHr());

        return leaveRequestRepo.save(existingRequest);
    }


    // delete leave Request by id
    public void deleteLeaveRequest(Long id) {
        if (!leaveRequestRepo.existsById(id)) {
            throw new RuntimeException("LeaveRequest not found with id " + id);
        }
        leaveRequestRepo.deleteById(id);
    }

    // get leave Request by id
    public Optional<LeaveRequest> getLeaveRequestById(Long id) {
        return leaveRequestRepo.findById(id);
    }

    // get all leave Requests
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepo.findAll();
    }

    // get leave requests by employeeId
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId) {
        return leaveRequestRepo.findByEmployeeId(employeeId);
    }

    // get leave requests by hrEmployeeId
    public List<LeaveRequest> getLeaveRequestsByHrEmployeeId(Long hrEmployeeId) {
        return leaveRequestRepo.findByHrEmployeeId(hrEmployeeId);
    }


    // get paginated leave requests by employeeId
    public Page<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId, Pageable pageable) {
        return leaveRequestRepo.findByEmployeeId(employeeId, pageable);
    }

    // get paginated leave requests by hrEmployeeId
    public Page<LeaveRequest> getLeaveRequestsByHrEmployeeId(Long hrEmployeeId, Pageable pageable) {
        return leaveRequestRepo.findByHrEmployeeId(hrEmployeeId, pageable);
    }
}