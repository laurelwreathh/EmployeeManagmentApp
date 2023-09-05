package com.example.spirngbackend.services;

import com.example.spirngbackend.mappers.EmployeeMapper;
import com.example.spirngbackend.models.Employee;
import com.example.spirngbackend.repositories.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(employeeRepository, employeeMapper);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void findAll() {
        //when
        List<Employee> employees = employeeService.findAll();

        //then
        assertThat(employees).isEqualTo(verify(employeeRepository.findAll()));
    }

    @Test
    void findOneById() {
    }

    @Test
    void save() {
    }

    @Test
    void testSave() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}