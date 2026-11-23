package com.vetclinika.vetclinika.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "pets")
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Кличка обязательна")
    @Size(min = 1, max = 50, message = "Кличка от 1 до 50 символов")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Возраст обязателен")
    @Min(value = 0, message = "Возраст не может быть отрицательным")
    @Max(value = 50, message = "Слишком большой возраст")
    @Column(name = "age")
    private Integer age;

    @Column(name = "color")
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Users owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "breed_id")
    private Breeds breed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "species_id")
    private Species species;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Users getOwner() { return owner; }
    public void setOwner(Users owner) { this.owner = owner; }

    public Breeds getBreed() { return breed; }
    public void setBreed(Breeds breed) { this.breed = breed; }

    public Species getSpecies() { return species; }
    public void setSpecies(Species species) { this.species = species; }
}