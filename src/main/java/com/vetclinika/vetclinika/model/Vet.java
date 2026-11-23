package com.vetclinika.vetclinika.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "vets")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 50, message = "Имя от 2 до 50 символов")
    @Column(name = "firstname")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    @Size(min = 2, max = 50, message = "Фамилия от 2 до 50 символов")
    @Column(name = "lastname")
    private String lastName;

    @NotBlank(message = "Телефон обязателен")
    @Column(name = "phone")
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "speciality_id")
    private Specialities speciality;

    @NotBlank(message = "Статус обязателен")
    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users user;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Specialities getSpeciality() { return speciality; }
    public void setSpeciality(Specialities speciality) { this.speciality = speciality; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }
}