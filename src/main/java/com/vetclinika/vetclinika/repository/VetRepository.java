package com.vetclinika.vetclinika.repository;

import com.vetclinika.vetclinika.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {
}