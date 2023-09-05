package com.example.spirngbackend.repositories;

import com.example.spirngbackend.enums.Role;
import com.example.spirngbackend.models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void shouldFindAndReturnEmployeeByEmail() {

        //given
        String email = "example@email.com";
        Employee employee = Employee
                .builder()
                .firstName("Test")
                .lastName("testLast")
                .email("example@email.com")
                .password("asdfasdfasdfadsf")
                .role(Role.EMPLOYEE).build();

        //when
        employeeRepository.save(employee);
        Employee foundedEmployee = employeeRepository.findByEmail(email).get();

        //then
        assertThat(foundedEmployee).isNotNull();
        assertThat(foundedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void shouldFindAndReturnEmployeeByEmailDoesNotExists() {

        //given
        String email = "example@email.com";

        //when
        boolean isFound = employeeRepository.findByEmail(email).isPresent();

        //then
        assertThat(isFound).isFalse();
    }
}