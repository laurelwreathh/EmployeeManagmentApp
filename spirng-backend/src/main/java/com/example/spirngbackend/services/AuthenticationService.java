package com.example.spirngbackend.services;

import com.example.spirngbackend.dtos.EmployeeDTO;
import com.example.spirngbackend.dtos.JwtRequest;
import com.example.spirngbackend.dtos.JwtResponse;
import com.example.spirngbackend.enums.Role;
import com.example.spirngbackend.models.Employee;
import com.example.spirngbackend.security.EmployeeDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final EmployeeService employeeService;

    private final JwtService jwtService;

    private final PasswordEncoder encoder;

    public JwtResponse authenticate(JwtRequest jwtRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jwtRequest.getEmail(),
                jwtRequest.getPassword()
        ));


        return new JwtResponse(jwtService.generateToken(
                new EmployeeDetails(employeeService.findOneByEmail(jwtRequest.getEmail()).orElseThrow())));
    }

    public JwtResponse register(JwtRequest jwtRequest) {
        Employee employee = Employee.builder()
                .firstName(jwtRequest.getFirstName())
                .lastName(jwtRequest.getLastName())
                .email(jwtRequest.getEmail())
                .password(encoder.encode(jwtRequest.getPassword()))
                .role(Role.EMPLOYEE)
                .build();


        employeeService.save(employee);

        return new JwtResponse(jwtService.generateToken(new EmployeeDetails(employee)));
    }
}
