package com.example.spirngbackend.services;

import com.example.spirngbackend.enums.Role;
import com.example.spirngbackend.models.Employee;
import com.example.spirngbackend.security.AuthenticationRequest;
import com.example.spirngbackend.security.AuthenticationResponse;
import com.example.spirngbackend.security.EmployeeDetails;
import com.example.spirngbackend.security.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final EmployeeService employeeService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(EmployeeService employeeService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        Employee employee = new Employee(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Role.EMPLOYEE
        );

        employeeService.save(employee);

        return new AuthenticationResponse(jwtService.generateToken(new EmployeeDetails(employee)));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        return new AuthenticationResponse(jwtService.generateToken(
                new EmployeeDetails(employeeService.findOneByEmail(request.getEmail()).orElseThrow())));
    }
}
