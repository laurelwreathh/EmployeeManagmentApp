package com.example.spirngbackend.services;

import com.example.spirngbackend.dtos.EmployeeDTO;
import com.example.spirngbackend.mappers.EmployeeMapper;
import com.example.spirngbackend.models.Employee;
import com.example.spirngbackend.repositories.EmployeeRepository;
import com.example.spirngbackend.utils.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    private final EmployeeMapper mapper;

    public List<Employee> findAll(){
        return repository.findAll();
    }

    public Employee findOneById(int id) throws EmployeeNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID:" + id + " not found"));
    }

    public Optional<Employee> findOneByEmail(String email){
        return repository.findByEmail(email);
    }

    @Transactional
    public Employee save(EmployeeDTO employeeDTO){
        return repository.save(mapper.toModel(employeeDTO));
    }

    @Transactional
    public Employee save(Employee employee){
        return repository.save(employee);
    }

    @Transactional
    public void delete(int id){
        repository.deleteById(id);
    }

    @Transactional
    public void update(Employee employee, int id){
        employee.setId(id);
        repository.save(employee);
    }


}
