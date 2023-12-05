package com.example.spirngbackend.controllers;

import com.example.spirngbackend.dtos.EmployeeDTO;
import com.example.spirngbackend.models.Employee;
import com.example.spirngbackend.services.AuthenticationService;
import com.example.spirngbackend.services.EmployeeService;
import com.example.spirngbackend.utils.EmployeeNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    private final PasswordEncoder encoder;

    @GetMapping()
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findOneById(@PathVariable("id") int id) throws EmployeeNotFoundException {
        return employeeService.findOneById(id);
    }

    @PostMapping()
    public ResponseEntity<EmployeeDTO> create(@RequestBody @Valid EmployeeDTO employeeDTO){
        employeeDTO.setPassword(encoder.encode(employeeDTO.getPassword()));
        employeeService.save(employeeDTO);
        return new ResponseEntity<>(employeeDTO ,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody @Valid Employee employee){
        employeeService.update(employee, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id){
        employeeService.delete(id);
    }
}
