package com.example.spirngbackend.models;

import com.example.spirngbackend.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Employee")
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

    public Employee(int id, String firstName, String lastName, String email, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Employee(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Employee(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + email + '\'' +'\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
