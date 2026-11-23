package com.vetclinika.vetclinika.service;

import com.vetclinika.vetclinika.model.Prescriptions;
import com.vetclinika.vetclinika.repository.PrescriptionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionsRepository prescriptionsRepository;

    public PrescriptionService(PrescriptionsRepository prescriptionsRepository) {
        this.prescriptionsRepository = prescriptionsRepository;
    }

    public List<Prescriptions> getAllPrescriptions() {
        return prescriptionsRepository.findAll();
    }

    public Prescriptions getPrescriptionById(Long id) {
        return prescriptionsRepository.findById(id).orElse(null);
    }

    public Prescriptions savePrescription(Prescriptions prescription) {
        return prescriptionsRepository.save(prescription);
    }

    public void deletePrescription(Long id) {
        prescriptionsRepository.deleteById(id);
    }

    public List<Prescriptions> searchPrescriptions(String keyword) {
        return prescriptionsRepository.findAll().stream()
                .filter(p ->
                        p.getMedication().toLowerCase().contains(keyword.toLowerCase()) ||
                        (p.getId() != null && p.getId().toString().equals(keyword))
                )
                .toList();
    }
}