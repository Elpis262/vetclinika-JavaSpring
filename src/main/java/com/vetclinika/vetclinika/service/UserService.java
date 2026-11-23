package com.vetclinika.vetclinika.service;

import com.vetclinika.vetclinika.model.Users;
import com.vetclinika.vetclinika.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Users> searchUsers(String keyword) {
        return userRepository.findAll().stream()
                .filter(u ->
                        u.getUsername().toLowerCase().contains(keyword.toLowerCase()) ||
                        (u.getId() != null && u.getId().toString().equals(keyword))
                )
                .toList();
    }
}