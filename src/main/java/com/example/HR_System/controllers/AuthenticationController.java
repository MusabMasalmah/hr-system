package com.example.HR_System.controllers;

import com.example.HR_System.AuthClasses.AuthenticateRequest;
import com.example.HR_System.AuthClasses.AuthenticationResponse;
import com.example.HR_System.AuthClasses.RegisterRequest;
import com.example.HR_System.dto.EmployeeDto;
import com.example.HR_System.repositories.EmployeeRepo;
import com.example.HR_System.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private EmployeeRepo userRepo;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticateRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}