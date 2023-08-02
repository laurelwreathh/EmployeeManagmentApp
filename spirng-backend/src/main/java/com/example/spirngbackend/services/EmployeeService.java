package com.example.spirngbackend.services;

import com.example.spirngbackend.dtos.EmployeeDTO;
import com.example.spirngbackend.mappers.EmployeeMapper;
import com.example.spirngbackend.models.Employee;
import com.example.spirngbackend.repositories.EmployeeRepository;
import com.example.spirngbackend.utils.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public EmployeeService(EmployeeRepository repository, EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Employee> findAll(){
        return repository.findAll();
    }

    public Employee findOneById(int id) throws EmployeeNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID:" + id + " not found"));
    }

    @Transactional
    public Employee save(EmployeeDTO employeeDTO){
        return repository.save(mapper.toModel(employeeDTO));
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
