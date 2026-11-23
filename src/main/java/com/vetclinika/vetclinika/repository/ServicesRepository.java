package com.vetclinika.vetclinika.repository;

import com.vetclinika.vetclinika.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<Services, Long> {
}