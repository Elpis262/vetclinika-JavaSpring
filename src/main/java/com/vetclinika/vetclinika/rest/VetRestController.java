package com.vetclinika.vetclinika.rest;

import com.vetclinika.vetclinika.model.Vet;
import com.vetclinika.vetclinika.service.VetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vets")
public class VetRestController {

    private final VetService vetService;

    public VetRestController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping
    public List<Vet> getAll() {
        return vetService.getAllVets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vet> getById(@PathVariable Long id) {
        Vet vet = vetService.getVetById(id);
        if (vet == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(vet);
    }

    @PostMapping
    public Vet create(@RequestBody Vet vet) {
        return vetService.saveVet(vet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vet> update(@PathVariable Long id, @RequestBody Vet vet) {
        Vet existing = vetService.getVetById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        vet.setId(id);
        return ResponseEntity.ok(vetService.saveVet(vet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vetService.deleteVet(id);
        return ResponseEntity.noContent().build();
    }
}