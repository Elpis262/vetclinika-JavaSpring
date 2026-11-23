package com.vetclinika.vetclinika.controller;

import com.vetclinika.vetclinika.model.Pets;
import com.vetclinika.vetclinika.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pets")
public class PetsController {

    private final PetsService petsService;
    private final UserService userService;
    private final BreedsService breedsService;
    private final SpeciesService speciesService;

    public PetsController(PetsService petsService, UserService userService,
                          BreedsService breedsService, SpeciesService speciesService) {
        this.petsService = petsService;
        this.userService = userService;
        this.breedsService = breedsService;
        this.speciesService = speciesService;
    }

    @GetMapping
    public String petsPage(Model model) {
        model.addAttribute("pets", petsService.getAllPets());
        model.addAttribute("pet", new Pets());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("breeds", breedsService.getAllBreeds());
        model.addAttribute("species", speciesService.getAllSpecies());
        return "pet_form";
    }

    @PostMapping("/save")
    public String savePet(@Valid @ModelAttribute("pet") Pets pet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pets", petsService.getAllPets());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("breeds", breedsService.getAllBreeds());
            model.addAttribute("species", speciesService.getAllSpecies());
            return "pet_form";
        }
        petsService.savePet(pet);
        return "redirect:/pets";
    }

    @GetMapping("/edit/{id}")
    public String editPet(@PathVariable Long id, Model model) {
        model.addAttribute("pet", petsService.getPetById(id));
        model.addAttribute("pets", petsService.getAllPets());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("breeds", breedsService.getAllBreeds());
        model.addAttribute("species", speciesService.getAllSpecies());
        return "pet_form";
    }

    @GetMapping("/delete/{id}")
    public String deletePet(@PathVariable Long id) {
        petsService.deletePet(id);
        return "redirect:/pets";
    }

    @GetMapping("/search")
    public String searchPets(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("pets", petsService.searchPets(keyword));
        model.addAttribute("pet", new Pets());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("breeds", breedsService.getAllBreeds());
        model.addAttribute("species", speciesService.getAllSpecies());
        model.addAttribute("keyword", keyword);
        return "pet_form";
    }
}