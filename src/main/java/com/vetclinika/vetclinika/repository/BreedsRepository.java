package com.vetclinika.vetclinika.repository;

import com.vetclinika.vetclinika.model.Breeds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedsRepository extends JpaRepository<Breeds, Long> {
}