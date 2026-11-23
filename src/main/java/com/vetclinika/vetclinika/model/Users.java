package com.vetclinika.vetclinika.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Логин обязателен")
    @Size(min = 3, max = 50, message = "Логин от 3 до 50 символов")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, message = "Пароль минимум 6 символов")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Неверный формат email")
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Roles role;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "status")
    private String status;

    @Column(name = "enabled")
    private Boolean enabled = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Roles getRole() { return role; }
    public void setRole(Roles role) { this.role = role; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
}