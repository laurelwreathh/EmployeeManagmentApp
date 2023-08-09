package com.example.spirngbackend.services;

import com.example.spirngbackend.dtos.EmployeeDTO;
import com.example.spirngbackend.dtos.JwtRequest;
import com.example.spirngbackend.enums.Role;
import com.example.spirngbackend.models.Employee;
import com.example.spirngbackend.security.EmployeeDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final EmployeeService employeeService;

    private final JwtService jwtService;

    private final PasswordEncoder encoder;

    public AuthenticationService(AuthenticationManager authenticationManager, EmployeeService employeeService, JwtService jwtService, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.employeeService = employeeService;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }


    public String authenticate(JwtRequest jwtRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jwtRequest.getEmail(),
                jwtRequest.getPassword()
        ));
        return jwtService.generateToken(
                new EmployeeDetails(employeeService.findOneByEmail(jwtRequest.getEmail()).orElseThrow()));
    }

    public String register(JwtRequest jwtRequest){
        Employee employee = new Employee(
                jwtRequest.getFirstName(),
                jwtRequest.getLastName(),
                jwtRequest.getEmail(),
                encoder.encode(jwtRequest.getPassword()),
                Role.EMPLOYEE
        );

        employeeService.save(employee);

        return jwtService.generateToken(new EmployeeDetails(employee));
    }
}
