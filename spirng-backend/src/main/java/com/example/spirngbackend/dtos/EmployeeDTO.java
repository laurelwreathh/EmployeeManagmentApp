package com.example.spirngbackend.dtos;

import com.example.spirngbackend.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeDTO {

    @NotNull(message = "First Name should be not null")
    @NotBlank(message = "First Name should be not empty")
    @Size(min = 3, max = 20, message = "First Name should be of 3-30 characters")
    private String firstName;

    @NotNull(message = "Last Name should be not null")
    @NotBlank(message = "Last Name should be not empty")
    private String lastName;

    @Email(message = "Invalid email")
    private String email;


    @Enumerated(EnumType.STRING)
    private Role role;

    public EmployeeDTO(String firstName, String lastName, String email, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }


    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public EmployeeDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
