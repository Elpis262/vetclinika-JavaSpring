package com.vetclinika.vetclinika.rest;

import com.vetclinika.vetclinika.model.Services;
import com.vetclinika.vetclinika.service.ClinicServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceRestController {

    private final ClinicServiceService clinicServiceService;

    public ServiceRestController(ClinicServiceService clinicServiceService) {
        this.clinicServiceService = clinicServiceService;
    }

    @GetMapping
    public List<Services> getAll() {
        return clinicServiceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Services> getById(@PathVariable Long id) {
        Services service = clinicServiceService.getServiceById(id);
        if (service == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(service);
    }

    @PostMapping
    public Services create(@RequestBody Services service) {
        return clinicServiceService.saveService(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Services> update(@PathVariable Long id, @RequestBody Services service) {
        Services existing = clinicServiceService.getServiceById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        service.setId(id);
        return ResponseEntity.ok(clinicServiceService.saveService(service));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clinicServiceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}