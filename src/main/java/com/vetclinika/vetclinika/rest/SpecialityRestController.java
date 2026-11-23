package com.vetclinika.vetclinika.rest;

import com.vetclinika.vetclinika.model.Specialities;
import com.vetclinika.vetclinika.service.SpecialityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialities")
public class SpecialityRestController {

    private final SpecialityService specialityService;

    public SpecialityRestController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping
    public List<Specialities> getAll() {
        return specialityService.getAllSpecialities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialities> getById(@PathVariable Long id) {
        Specialities speciality = specialityService.getSpecialityById(id);
        if (speciality == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(speciality);
    }

    @PostMapping
    public Specialities create(@RequestBody Specialities speciality) {
        return specialityService.saveSpeciality(speciality);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specialities> update(@PathVariable Long id, @RequestBody Specialities speciality) {
        Specialities existing = specialityService.getSpecialityById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        speciality.setId(id);
        return ResponseEntity.ok(specialityService.saveSpeciality(speciality));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        specialityService.deleteSpeciality(id);
        return ResponseEntity.noContent().build();
    }
}