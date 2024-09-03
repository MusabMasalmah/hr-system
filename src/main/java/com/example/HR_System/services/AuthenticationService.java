package com.example.HR_System.services;

import com.example.HR_System.AuthClasses.AuthenticateRequest;
import com.example.HR_System.AuthClasses.AuthenticationResponse;
import com.example.HR_System.AuthClasses.RegisterRequest;
import com.example.HR_System.models.Employee;
import com.example.HR_System.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private EmployeeRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        var user = new Employee();

        userRepo.save(user);
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        try {
            // Attempt to authenticate the user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            // Find the user by email
            var user = userRepo.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Generate JWT token
            var jwtToken = jwtService.generateToken((UserDetails) user);

            // Return the authentication response with the token
            return new AuthenticationResponse(jwtToken);

        } catch (Exception e) {
            // Log the exception and rethrow it or return a meaningful error response
            System.out.println("Authentication failed: " + e.getMessage());
            throw e;
        }
    }

}