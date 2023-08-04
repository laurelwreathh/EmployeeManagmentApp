package com.example.spirngbackend.config;

import com.example.spirngbackend.security.EmployeeDetails;
import com.example.spirngbackend.services.EmployeeService;
import com.example.spirngbackend.utils.EmployeeNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class ApplicationConfig {

    private final EmployeeService employeeService;

    public ApplicationConfig(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Bean
    public UserDetailsService employeeDetailsService() {
        return username -> new EmployeeDetails(employeeService.findOneByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found")));
    }
}
