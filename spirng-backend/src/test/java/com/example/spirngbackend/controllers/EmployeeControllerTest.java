package com.example.spirngbackend.controllers;

import com.example.spirngbackend.dtos.EmployeeDTO;
import com.example.spirngbackend.enums.Role;
import com.example.spirngbackend.mappers.EmployeeMapper;
import com.example.spirngbackend.models.Employee;
import com.example.spirngbackend.services.EmployeeService;
import com.example.spirngbackend.services.JwtService;
import com.example.spirngbackend.utils.EmployeeNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private EmployeeDTO employeeDTO;

    private Employee employee;

    private List<Employee> employeeList;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        employeeDTO = EmployeeDTO
                .builder()
                .firstName("Test")
                .lastName("testLast")
                .email("example@email.com")
                .password("asdfasdfasdfadsf")
                .role(Role.EMPLOYEE).build();

        employee = Employee
                .builder()
                .id(1)
                .firstName("Test")
                .lastName("testLast")
                .email("example@email.com")
                .password("asdfasdfasdfadsf")
                .role(Role.EMPLOYEE).build();

        employeeList = Collections.singletonList(employee);
    }

    @Test
    void findAll() throws Exception {
        given(employeeService.findAll()).willReturn(employeeList);

        ResultActions response = mockMvc.perform(get("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(employeeList.size())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void findOneById() throws Exception {
        given(employeeService.findOneById(employee.getId())).willReturn(employee);

        ResultActions response = mockMvc.perform(get("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(employee.getEmail())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void create() throws Exception {
        given(employeeService.save((Employee) ArgumentMatchers.any()))
                .willAnswer(InvocationOnMock::getArguments);

        ResultActions request = mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)));

        request.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(employeeDTO.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(employeeDTO.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(employeeDTO.getEmail())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void update() {

    }

    @Test
    void delete() {
    }
}