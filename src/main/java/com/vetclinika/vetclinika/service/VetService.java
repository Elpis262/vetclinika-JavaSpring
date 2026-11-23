package com.vetclinika.vetclinika.service;

import com.vetclinika.vetclinika.model.Vet;
import com.vetclinika.vetclinika.repository.VetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetService {

    private final VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    public List<Vet> getAllVets() {
        return vetRepository.findAll();
    }

    public Vet getVetById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    public Vet saveVet(Vet vet) {
        return vetRepository.save(vet);
    }

    public void deleteVet(Long id) {
        vetRepository.deleteById(id);
    }

    public List<Vet> searchVets(String keyword) {
        return vetRepository.findAll().stream()
                .filter(v ->
                        v.getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                        v.getLastName().toLowerCase().contains(keyword.toLowerCase()) ||
                        (v.getId() != null && v.getId().toString().equals(keyword))
                )
                .toList();
    }
}