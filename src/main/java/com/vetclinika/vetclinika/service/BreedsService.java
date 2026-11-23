package com.vetclinika.vetclinika.service;

import com.vetclinika.vetclinika.model.Breeds;
import com.vetclinika.vetclinika.repository.BreedsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedsService {

    private final BreedsRepository breedsRepository;

    public BreedsService(BreedsRepository breedsRepository) {
        this.breedsRepository = breedsRepository;
    }

    public List<Breeds> getAllBreeds() {
        return breedsRepository.findAll();
    }

    public Breeds getBreedById(Long id) {
        return breedsRepository.findById(id).orElse(null);
    }

    public Breeds saveBreed(Breeds breed) {
        return breedsRepository.save(breed);
    }

    public void deleteBreed(Long id) {
        breedsRepository.deleteById(id);
    }

    public List<Breeds> searchBreeds(String keyword) {
        return breedsRepository.findAll().stream()
                .filter(b ->
                        b.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        (b.getId() != null && b.getId().toString().equals(keyword))
                )
                .toList();
    }
}