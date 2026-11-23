package com.vetclinika.vetclinika.service;

import com.vetclinika.vetclinika.model.Specialities;
import com.vetclinika.vetclinika.repository.SpecialitiesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {

    private final SpecialitiesRepository specialitiesRepository;

    public SpecialityService(SpecialitiesRepository specialitiesRepository) {
        this.specialitiesRepository = specialitiesRepository;
    }

    public List<Specialities> getAllSpecialities() {
        return specialitiesRepository.findAll();
    }

    public Specialities getSpecialityById(Long id) {
        return specialitiesRepository.findById(id).orElse(null);
    }

    public Specialities saveSpeciality(Specialities speciality) {
        return specialitiesRepository.save(speciality);
    }

    public void deleteSpeciality(Long id) {
        specialitiesRepository.deleteById(id);
    }

    public List<Specialities> searchSpecialities(String keyword) {
        return specialitiesRepository.findAll().stream()
                .filter(s ->
                        s.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        (s.getId() != null && s.getId().toString().equals(keyword))
                )
                .toList();
    }
}