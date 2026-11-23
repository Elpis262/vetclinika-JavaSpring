package com.vetclinika.vetclinika.rest;

import com.vetclinika.vetclinika.model.Prescriptions;
import com.vetclinika.vetclinika.service.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionRestController {

    private final PrescriptionService prescriptionService;

    public PrescriptionRestController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public List<Prescriptions> getAll() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescriptions> getById(@PathVariable Long id) {
        Prescriptions prescription = prescriptionService.getPrescriptionById(id);
        if (prescription == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(prescription);
    }

    @PostMapping
    public Prescriptions create(@RequestBody Prescriptions prescription) {
        return prescriptionService.savePrescription(prescription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescriptions> update(@PathVariable Long id, @RequestBody Prescriptions prescription) {
        Prescriptions existing = prescriptionService.getPrescriptionById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        prescription.setId(id);
        return ResponseEntity.ok(prescriptionService.savePrescription(prescription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}