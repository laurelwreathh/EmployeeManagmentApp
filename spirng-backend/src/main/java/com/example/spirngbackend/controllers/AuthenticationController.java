package com.example.spirngbackend.controllers;

import com.example.spirngbackend.dtos.EmployeeDTO;
import com.example.spirngbackend.dtos.JwtRequest;
import com.example.spirngbackend.dtos.JwtResponse;
import com.example.spirngbackend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody JwtRequest jwtRequest){
        return new ResponseEntity<>(authenticationService.register(jwtRequest), HttpStatus.CREATED);

    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest jwtRequest){
        return new ResponseEntity<>(authenticationService.authenticate(jwtRequest), HttpStatus.ACCEPTED);

    }
}
