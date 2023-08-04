package com.example.spirngbackend.controllers;

import com.example.spirngbackend.dtos.EmployeeDTO;
import com.example.spirngbackend.mappers.EmployeeMapper;
import com.example.spirngbackend.models.Employee;
import com.example.spirngbackend.services.EmployeeService;
import com.example.spirngbackend.utils.EmployeeNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findOneById(@PathVariable("id") int id) throws EmployeeNotFoundException {
        return employeeService.findOneById(id);
    }

    @PostMapping()
    public ResponseEntity<Employee> create(@RequestBody @Valid EmployeeDTO employeeDTO){
        return new ResponseEntity<>(employeeService.save(employeeDTO), HttpStatus.CREATED);

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
