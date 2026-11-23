package com.vetclinika.vetclinika.service;

import com.vetclinika.vetclinika.model.Services;
import com.vetclinika.vetclinika.repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceService {

    private final ServicesRepository servicesRepository;

    public ClinicServiceService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    public Services getServiceById(Long id) {
        return servicesRepository.findById(id).orElse(null);
    }

    public Services saveService(Services service) {
        return servicesRepository.save(service);
    }

    public void deleteService(Long id) {
        servicesRepository.deleteById(id);
    }

    public List<Services> searchServices(String keyword) {
        return servicesRepository.findAll().stream()
                .filter(s ->
                        s.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        (s.getId() != null && s.getId().toString().equals(keyword))
                )
                .toList();
    }
}