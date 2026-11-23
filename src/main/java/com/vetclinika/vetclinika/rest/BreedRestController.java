package com.vetclinika.vetclinika.rest;

import com.vetclinika.vetclinika.model.Breeds;
import com.vetclinika.vetclinika.service.BreedsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breeds")
public class BreedRestController {

    private final BreedsService breedsService;

    public BreedRestController(BreedsService breedsService) {
        this.breedsService = breedsService;
    }

    @GetMapping
    public List<Breeds> getAll() {
        return breedsService.getAllBreeds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Breeds> getById(@PathVariable Long id) {
        Breeds breed = breedsService.getBreedById(id);
        if (breed == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(breed);
    }

    @PostMapping
    public Breeds create(@RequestBody Breeds breed) {
        return breedsService.saveBreed(breed);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Breeds> update(@PathVariable Long id, @RequestBody Breeds breed) {
        Breeds existing = breedsService.getBreedById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        breed.setId(id);
        return ResponseEntity.ok(breedsService.saveBreed(breed));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        breedsService.deleteBreed(id);
        return ResponseEntity.noContent().build();
    }
}