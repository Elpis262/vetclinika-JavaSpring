package com.vetclinika.vetclinika.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "prescriptions")
public class Prescriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название препарата обязательно")
    @Size(min = 2, max = 100, message = "Название от 2 до 100 символов")
    @Column(name = "medication")
    private String medication;

    @NotBlank(message = "Дозировка обязательна")
    @Column(name = "dosage")
    private String dosage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id")
    private Appointments appointment;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public Appointments getAppointment() { return appointment; }
    public void setAppointment(Appointments appointment) { this.appointment = appointment; }
}