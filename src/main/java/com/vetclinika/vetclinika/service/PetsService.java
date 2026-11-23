package com.vetclinika.vetclinika.service;

import com.vetclinika.vetclinika.model.Pets;
import com.vetclinika.vetclinika.repository.PetsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetsService {

    private final PetsRepository petsRepository;

    public PetsService(PetsRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

    public List<Pets> getAllPets() {
        return petsRepository.findAll();
    }

    public Pets getPetById(Long id) {
        return petsRepository.findById(id).orElse(null);
    }

    public Pets savePet(Pets pet) {
        return petsRepository.save(pet);
    }

    public void deletePet(Long id) {
        petsRepository.deleteById(id);
    }

    public List<Pets> searchPets(String keyword) {
        return petsRepository.findAll().stream()
                .filter(p ->
                        p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        (p.getId() != null && p.getId().toString().equals(keyword))
                )
                .toList();
    }
}