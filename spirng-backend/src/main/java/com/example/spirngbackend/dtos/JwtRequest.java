package com.example.spirngbackend.dtos;

import com.example.spirngbackend.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequest {

    @Size(min = 3, max = 20, message = "First Name should be of 3-30 characters")
    private String firstName;

    private String lastName;

    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Password should be not null")
    @NotBlank(message = "Password should be not empty")
    @Size(min = 8, message = "Password is too short")
    private String password;

}
