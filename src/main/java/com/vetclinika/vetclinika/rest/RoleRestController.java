package com.vetclinika.vetclinika.rest;

import com.vetclinika.vetclinika.model.Roles;
import com.vetclinika.vetclinika.service.RolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    private final RolesService rolesService;

    public RoleRestController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public List<Roles> getAll() {
        return rolesService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roles> getById(@PathVariable Long id) {
        Roles role = rolesService.getRoleById(id);
        if (role == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public Roles create(@RequestBody Roles role) {
        return rolesService.saveRole(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Roles> update(@PathVariable Long id, @RequestBody Roles role) {
        Roles existing = rolesService.getRoleById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        role.setId(id);
        return ResponseEntity.ok(rolesService.saveRole(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rolesService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}