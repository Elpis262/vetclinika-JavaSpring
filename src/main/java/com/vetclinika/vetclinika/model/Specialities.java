package com.vetclinika.vetclinika.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "specialities")
public class Specialities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название специальности обязательно")
    @Size(min = 2, max = 100, message = "Название от 2 до 100 символов")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Описание обязательно")
    @Column(name = "description")
    private String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}