package com.vetclinika.vetclinika.controller;

import com.vetclinika.vetclinika.model.Roles;
import com.vetclinika.vetclinika.model.Users;
import com.vetclinika.vetclinika.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final RolesService rolesService;

    public UsersController(UserService userService, RolesService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }

    @GetMapping
    public String usersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new Users());
        model.addAttribute("roles", rolesService.getAllRoles());
        return "users_form";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("roles", rolesService.getAllRoles());
            return "users_form";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", rolesService.getAllRoles());
        return "users_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("users", userService.searchUsers(keyword));
        model.addAttribute("user", new Users());
        model.addAttribute("roles", rolesService.getAllRoles());
        model.addAttribute("keyword", keyword);
        return "users_form";
    }

    @GetMapping("/manage-roles")
    public String manageRolesPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", rolesService.getAllRoles());
        return "manage_roles";
    }

    @PostMapping("/change-role")
    public String changeRole(@RequestParam("userId") Long userId,
                             @RequestParam("roleId") Long roleId) {
        Users user = userService.getUserById(userId);
        Roles role = rolesService.getRoleById(roleId);

        if (user != null && role != null) {
            user.setRole(role);
            userService.saveUser(user);
        }

        return "redirect:/users/manage-roles";
    }
}