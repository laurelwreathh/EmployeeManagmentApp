package com.example.spirngbackend.dtos;

import com.example.spirngbackend.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @NotNull(message = "Password should be not null")
    @NotBlank(message = "Password should be not empty")
    @Size(min = 8, message = "Password is too short")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


}
