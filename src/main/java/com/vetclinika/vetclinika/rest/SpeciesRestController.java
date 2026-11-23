package com.vetclinika.vetclinika.rest;

import com.vetclinika.vetclinika.model.Species;
import com.vetclinika.vetclinika.service.SpeciesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species")
public class SpeciesRestController {

    private final SpeciesService speciesService;

    public SpeciesRestController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @GetMapping
    public List<Species> getAll() {
        return speciesService.getAllSpecies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Species> getById(@PathVariable Long id) {
        Species specy = speciesService.getSpeciesById(id);
        if (specy == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(specy);
    }

    @PostMapping
    public Species create(@RequestBody Species specy) {
        return speciesService.saveSpecies(specy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Species> update(@PathVariable Long id, @RequestBody Species specy) {
        Species existing = speciesService.getSpeciesById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        specy.setId(id);
        return ResponseEntity.ok(speciesService.saveSpecies(specy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        speciesService.deleteSpecies(id);
        return ResponseEntity.noContent().build();
    }
}