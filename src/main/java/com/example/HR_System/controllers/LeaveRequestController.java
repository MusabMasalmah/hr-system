package com.example.HR_System.controllers;

import com.example.HR_System.models.LeaveRequest;
import com.example.HR_System.services.LeaveRequestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v0/leave-requests")
@CrossOrigin(origins = "http://localhost:4200")
public class LeaveRequestController {

    private final LeaveRequestServices leaveRequestServices;

    @Autowired
    public LeaveRequestController(LeaveRequestServices leaveRequestServices) {
        this.leaveRequestServices = leaveRequestServices;
    }

    // add leave Request
    @PostMapping("/addLeaveRequest")
    public ResponseEntity<LeaveRequest> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        LeaveRequest createdLeaveRequest = leaveRequestServices.createLeaveRequest(leaveRequest);
        return new ResponseEntity<>(createdLeaveRequest, HttpStatus.CREATED);
    }

    // update leave Request by hr
    @PutMapping("/hr/update/{id}")
    public ResponseEntity<LeaveRequest> updateLeaveRequestByHR(
            @PathVariable Long id,
            @RequestBody LeaveRequest leaveRequest) {
        LeaveRequest updatedLeaveRequest = leaveRequestServices.updateLeaveRequestByHR(id, leaveRequest);
        return new ResponseEntity<>(updatedLeaveRequest, HttpStatus.OK);
    }

    // update leave Request by employee
    @PutMapping("/employee/update/{id}")
    public ResponseEntity<LeaveRequest> updateLeaveRequestByEmployee(
            @PathVariable Long id,
            @RequestBody LeaveRequest leaveRequest) {
        LeaveRequest updatedLeaveRequest = leaveRequestServices.updateLeaveRequestByEmployee(id, leaveRequest);
        return new ResponseEntity<>(updatedLeaveRequest, HttpStatus.OK);
    }
    // delete leave Request by employee
    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(@PathVariable Long id) {
        leaveRequestServices.deleteLeaveRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // get leave request By requestId
    @GetMapping("/getLeaveRequest/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable Long id) {
        Optional<LeaveRequest> leaveRequest = leaveRequestServices.getLeaveRequestById(id);
        return leaveRequest.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // get all leave Request
    @GetMapping("/getAllLeaveRequests")
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestServices.getAllLeaveRequests();
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }


    // Get leave requests by employeeId
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequest>> getLeaveRequestsByEmployeeId(@PathVariable Long employeeId) {
        List<LeaveRequest> leaveRequests = leaveRequestServices.getLeaveRequestsByEmployeeId(employeeId);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    // Get leave requests by hrEmployeeId
    @GetMapping("/hr/{hrEmployeeId}")
    public ResponseEntity<List<LeaveRequest>> getLeaveRequestsByHrEmployeeId(@PathVariable Long hrEmployeeId) {
        List<LeaveRequest> leaveRequests = leaveRequestServices.getLeaveRequestsByHrEmployeeId(hrEmployeeId);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    // Get leave requests by EmployeeId pagination
    @GetMapping("/employee")
    public ResponseEntity<Page<LeaveRequest>> getLeaveRequestsByEmployeeId(
            @RequestParam Long employeeId,
            Pageable pageable) {
        Page<LeaveRequest> leaveRequests = leaveRequestServices.getLeaveRequestsByEmployeeId(employeeId, pageable);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    // Get leave requests by hrId pagination
    @GetMapping("/hr")
    public ResponseEntity<Page<LeaveRequest>> getLeaveRequestsByHrEmployeeId(
            @RequestParam Long hrEmployeeId,
            Pageable pageable) {
        Page<LeaveRequest> leaveRequests = leaveRequestServices.getLeaveRequestsByHrEmployeeId(hrEmployeeId, pageable);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }
}
