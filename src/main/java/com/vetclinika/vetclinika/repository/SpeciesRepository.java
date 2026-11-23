package com.vetclinika.vetclinika.repository;

import com.vetclinika.vetclinika.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
}