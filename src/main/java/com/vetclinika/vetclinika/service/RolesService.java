package com.vetclinika.vetclinika.service;

import com.vetclinika.vetclinika.model.Roles;
import com.vetclinika.vetclinika.repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    public Roles getRoleById(Long id) {
        return rolesRepository.findById(id).orElse(null);
    }

    public Roles saveRole(Roles role) {
        return rolesRepository.save(role);
    }

    public void deleteRole(Long id) {
        rolesRepository.deleteById(id);
    }

    public List<Roles> searchRoles(String keyword) {
        return rolesRepository.findAll().stream()
                .filter(r ->
                        r.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        (r.getId() != null && r.getId().toString().equals(keyword))
                )
                .toList();
    }
}