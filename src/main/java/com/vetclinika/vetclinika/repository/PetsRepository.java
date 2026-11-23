package com.vetclinika.vetclinika.repository;

import com.vetclinika.vetclinika.model.Pets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetsRepository extends JpaRepository<Pets, Long> {
}