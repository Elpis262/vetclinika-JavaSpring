package com.vetclinika.vetclinika.rest;

import com.vetclinika.vetclinika.model.Users;
import com.vetclinika.vetclinika.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getById(@PathVariable Long id) {
        Users user = userService.getUserById(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public Users create(@RequestBody Users user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable Long id, @RequestBody Users user) {
        Users existing = userService.getUserById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        user.setId(id);
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}