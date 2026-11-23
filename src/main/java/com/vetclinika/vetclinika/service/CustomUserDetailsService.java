package com.vetclinika.vetclinika.service;

import com.vetclinika.vetclinika.model.Users;
import com.vetclinika.vetclinika.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));

        String roleName = user.getRole() != null ? user.getRole().getName() : "ROLE_USER";

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled() != null ? user.getEnabled() : true,
                true, true, true,
                Collections.singletonList(new SimpleGrantedAuthority(roleName))
        );
    }
}