package com.vetclinika.vetclinika.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Дата и время обязательны")
    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @NotBlank(message = "Статус обязателен")
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pets pet;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Pets getPet() { return pet; }
    public void setPet(Pets pet) { this.pet = pet; }

    public Vet getVet() { return vet; }
    public void setVet(Vet vet) { this.vet = vet; }

    public Services getService() { return service; }
    public void setService(Services service) { this.service = service; }
}