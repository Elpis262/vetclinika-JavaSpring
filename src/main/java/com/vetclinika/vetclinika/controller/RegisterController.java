package com.vetclinika.vetclinika.controller;

import com.vetclinika.vetclinika.model.Roles;
import com.vetclinika.vetclinika.model.Users;
import com.vetclinika.vetclinika.service.RolesService;
import com.vetclinika.vetclinika.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UserService userService;
    private final RolesService rolesService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, RolesService rolesService,
                              PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.rolesService = rolesService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") Users user,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Roles userRole = rolesService.getAllRoles().stream()
                .filter(r -> r.getName().equals("ROLE_USER"))
                .findFirst()
                .orElse(null);

        user.setRole(userRole);
        user.setEnabled(true);
        user.setStatus("active");

        userService.saveUser(user);

        return "redirect:/?registered";
    }
}