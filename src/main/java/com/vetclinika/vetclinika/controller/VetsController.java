package com.vetclinika.vetclinika.controller;

import com.vetclinika.vetclinika.model.Vet;
import com.vetclinika.vetclinika.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vets")
public class VetsController {

    private final VetService vetService;
    private final SpecialityService specialityService;
    private final UserService userService;

    public VetsController(VetService vetService, SpecialityService specialityService, UserService userService) {
        this.vetService = vetService;
        this.specialityService = specialityService;
        this.userService = userService;
    }

    @GetMapping
    public String vetsPage(Model model) {
        model.addAttribute("vets", vetService.getAllVets());
        model.addAttribute("vet", new Vet());
        model.addAttribute("specialities", specialityService.getAllSpecialities());
        model.addAttribute("users", userService.getAllUsers());
        return "vet_form";
    }

    @PostMapping("/save")
    public String saveVet(@Valid @ModelAttribute("vet") Vet vet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("vets", vetService.getAllVets());
            model.addAttribute("specialities", specialityService.getAllSpecialities());
            model.addAttribute("users", userService.getAllUsers());
            return "vet_form";
        }
        vetService.saveVet(vet);
        return "redirect:/vets";
    }

    @GetMapping("/edit/{id}")
    public String editVet(@PathVariable Long id, Model model) {
        model.addAttribute("vet", vetService.getVetById(id));
        model.addAttribute("vets", vetService.getAllVets());
        model.addAttribute("specialities", specialityService.getAllSpecialities());
        model.addAttribute("users", userService.getAllUsers());
        return "vet_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteVet(@PathVariable Long id) {
        vetService.deleteVet(id);
        return "redirect:/vets";
    }

    @GetMapping("/search")
    public String searchVets(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("vets", vetService.searchVets(keyword));
        model.addAttribute("vet", new Vet());
        model.addAttribute("specialities", specialityService.getAllSpecialities());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("keyword", keyword);
        return "vet_form";
    }
}