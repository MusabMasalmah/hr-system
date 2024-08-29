package com.example.HR_System.services;

import com.example.HR_System.models.LeaveRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LeaveRequestServiceInterface {

    // add leave request
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest);


    // update by employee (can update all fields except status and reasonForHr)
    public LeaveRequest updateLeaveRequestByEmployee(Long id, LeaveRequest leaveRequest);


    // update by HR (can update only the status and reasonForHr)
    public LeaveRequest updateLeaveRequestByHR(Long id, LeaveRequest leaveRequest);


    // delete leave Request by id
    public void deleteLeaveRequest(Long id);


    // get leave Request by id
    public Optional<LeaveRequest> getLeaveRequestById(Long id);


    // get all leave Requests
    public List<LeaveRequest> getAllLeaveRequests();


    // get leave requests by employeeId
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId);


    // get leave requests by hrEmployeeId
    public List<LeaveRequest> getLeaveRequestsByHrEmployeeId(Long hrEmployeeId);


    // get paginated leave requests by employeeId
    public Page<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId, Pageable pageable);


    // get paginated leave requests by hrEmployeeId
    public Page<LeaveRequest> getLeaveRequestsByHrEmployeeId(Long hrEmployeeId, Pageable pageable);


}
