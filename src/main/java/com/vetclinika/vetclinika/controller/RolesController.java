package com.vetclinika.vetclinika.controller;

import com.vetclinika.vetclinika.model.Roles;
import com.vetclinika.vetclinika.service.RolesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public String rolesPage(Model model) {
        model.addAttribute("roles", rolesService.getAllRoles());
        model.addAttribute("role", new Roles());
        return "roles_form";
    }

    @PostMapping("/save")
    public String saveRole(@Valid @ModelAttribute("role") Roles role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", rolesService.getAllRoles());
            return "roles_form";
        }
        rolesService.saveRole(role);
        return "redirect:/roles";
    }

    @GetMapping("/edit/{id}")
    public String editRole(@PathVariable Long id, Model model) {
        model.addAttribute("role", rolesService.getRoleById(id));
        model.addAttribute("roles", rolesService.getAllRoles());
        return "roles_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        rolesService.deleteRole(id);
        return "redirect:/roles";
    }

    @GetMapping("/search")
    public String searchRoles(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("roles", rolesService.searchRoles(keyword));
        model.addAttribute("role", new Roles());
        model.addAttribute("keyword", keyword);
        return "roles_form";
    }
}