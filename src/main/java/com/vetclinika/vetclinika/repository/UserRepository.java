package com.vetclinika.vetclinika.repository;

import com.vetclinika.vetclinika.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}