package com.vetclinika.vetclinika.service;

import com.vetclinika.vetclinika.model.Species;
import com.vetclinika.vetclinika.repository.SpeciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public List<Species> getAllSpecies() {
        return speciesRepository.findAll();
    }

    public Species getSpeciesById(Long id) {
        return speciesRepository.findById(id).orElse(null);
    }

    public Species saveSpecies(Species species) {
        return speciesRepository.save(species);
    }

    public void deleteSpecies(Long id) {
        speciesRepository.deleteById(id);
    }

    public List<Species> searchSpecies(String keyword) {
        return speciesRepository.findAll().stream()
                .filter(s ->
                        s.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        (s.getId() != null && s.getId().toString().equals(keyword))
                )
                .toList();
    }
}