package com.vetclinika.vetclinika.repository;

import com.vetclinika.vetclinika.model.Prescriptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionsRepository extends JpaRepository<Prescriptions, Long> {
}