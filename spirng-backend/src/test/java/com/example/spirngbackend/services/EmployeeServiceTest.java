package com.example.spirngbackend.services;

import com.example.spirngbackend.dtos.EmployeeDTO;
import com.example.spirngbackend.enums.Role;
import com.example.spirngbackend.mappers.EmployeeMapper;
import com.example.spirngbackend.models.Employee;
import com.example.spirngbackend.repositories.EmployeeRepository;
import com.example.spirngbackend.utils.EmployeeNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1)
                .firstName("Alexander")
                .lastName("Kaplun")
                .email("test@mail.com")
                .role(Role.EMPLOYEE)
                .password("49868484854")
                .build();
    }

    @Test
    void canFindAll() {
        List<Employee> list = new ArrayList<>();
        list.add(employee);

        given(employeeRepository.findAll()).willReturn(list);

        //when
        List<Employee> foundedEmployees = employeeService.findAll();

        //then
        assertThat(foundedEmployees).isNotNull();
    }

    @Test
    void canFindOneById() throws EmployeeNotFoundException {

        given(employeeRepository.findById(employee.getId()))
                .willReturn(Optional.ofNullable(employee));

        //when
        Employee foundedEmployee = employeeService.findOneById(employee.getId());

        //then
        assertThat(foundedEmployee).isNotNull();
    }

    @Test
    void canSave() {

        given(employeeRepository.save(any(Employee.class))).willReturn(employee);

        Employee savedEmployee = employeeService.save(employee);

        assertThat(savedEmployee).isNotNull();
    }

    @Test
    void delete() {
        assertAll(() -> employeeService.delete(1));

    }

    @Test
    void canUpdate() {
        //given
        given(employeeRepository.save(any(Employee.class))).willReturn(employee);


        //when
        Employee updatedEmployee = employeeService.update(employee,1);

        //then
        assertThat(updatedEmployee).isNotNull();
    }
}