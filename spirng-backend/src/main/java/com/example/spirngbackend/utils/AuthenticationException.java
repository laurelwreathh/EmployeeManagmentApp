package com.example.spirngbackend.utils;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
@AllArgsConstructor
public class AuthenticationException extends Exception{

    private String message;
}
