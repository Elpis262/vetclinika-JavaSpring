package com.vetclinika.vetclinika.repository;

import com.vetclinika.vetclinika.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
}