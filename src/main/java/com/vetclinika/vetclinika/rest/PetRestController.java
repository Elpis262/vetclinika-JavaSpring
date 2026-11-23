package com.vetclinika.vetclinika.rest;

import com.vetclinika.vetclinika.model.Pets;
import com.vetclinika.vetclinika.service.PetsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetRestController {

    private final PetsService petsService;

    public PetRestController(PetsService petsService) {
        this.petsService = petsService;
    }

    @GetMapping
    public List<Pets> getAll() {
        return petsService.getAllPets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pets> getById(@PathVariable Long id) {
        Pets pet = petsService.getPetById(id);
        if (pet == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pet);
    }

    @PostMapping
    public Pets create(@RequestBody Pets pet) {
        return petsService.savePet(pet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pets> update(@PathVariable Long id, @RequestBody Pets pet) {
        Pets existing = petsService.getPetById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        pet.setId(id);
        return ResponseEntity.ok(petsService.savePet(pet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        petsService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}