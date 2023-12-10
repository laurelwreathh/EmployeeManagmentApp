package com.example.spirngbackend.services;

import com.example.spirngbackend.controllers.EmployeeController;
import com.example.spirngbackend.dtos.JwtRequest;
import com.example.spirngbackend.dtos.JwtResponse;
import com.example.spirngbackend.enums.Role;
import com.example.spirngbackend.models.Employee;
import com.example.spirngbackend.security.EmployeeDetails;
import org.mockito.Mock;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder encoder;

    @Test
    void authenticate() {
//        JwtRequest jwtRequest = JwtRequest
//                .builder()
//                .email("test@mail.com")
//                .password("testtesttest")
//                .build();
//
//        given(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                jwtRequest.getEmail(),
//                jwtRequest.getPassword()
//        ))).willReturn(any());
//
//        JwtResponse jwtResponse = JwtResponse
//                .builder()
//                .token(jwtService.generateToken(new EmployeeDetails(any())))
//                .build();
//
//        assertThat(jwtResponse).isNotNull();
    }

    @Test
    void register() {
        Employee employee = Employee.builder()
                .firstName("asdfasdfdsaf")
                .lastName("asdfasdfd")
                .email("safsadfgf@fdfsf.com")
                .password("password")
                .role(Role.EMPLOYEE)
                .build();

        employeeService.save(employee);

        JwtResponse response = new JwtResponse(jwtService.generateToken(new EmployeeDetails(employee)));

        assertThat(response).isNotNull();

    }
}