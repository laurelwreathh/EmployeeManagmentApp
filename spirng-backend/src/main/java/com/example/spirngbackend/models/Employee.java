package com.example.spirngbackend.models;

import com.example.spirngbackend.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Employee")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @NotNull(message = "First Name should be not null")
    @NotBlank(message = "First Name should be not empty")
    @Size(min = 3, max = 20, message = "First Name should be of 3-30 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last Name should be not null")
    @NotBlank(message = "Last Name should be not empty")
    private String lastName;

    @Email(message = "Invalid email")
    @Column(name = "email")
    @Email(message = "Invalid email")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Password should be not null")
    @NotBlank(message = "Password should be not empty")
    @Size(min = 8, message = "Password is too short")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    //TODO: разобраться с конструкторами, а то ниче не понятно ваще (need add lombok)
    //TODO: сделать валидацию
    //TODO: сделать экспешены

}
