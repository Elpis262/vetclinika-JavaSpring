package com.vetclinika.vetclinika.controller;

import com.vetclinika.vetclinika.model.Breeds;
import com.vetclinika.vetclinika.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/breeds")
public class BreedsController {

    private final BreedsService breedsService;
    private final SpeciesService speciesService;

    public BreedsController(BreedsService breedsService, SpeciesService speciesService) {
        this.breedsService = breedsService;
        this.speciesService = speciesService;
    }

    @GetMapping
    public String breedsPage(Model model) {
        model.addAttribute("breeds", breedsService.getAllBreeds());
        model.addAttribute("breed", new Breeds());
        model.addAttribute("species", speciesService.getAllSpecies());
        return "breeds_form";
    }

    @PostMapping("/save")
    public String saveBreed(@Valid @ModelAttribute("breed") Breeds breed, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("breeds", breedsService.getAllBreeds());
            model.addAttribute("species", speciesService.getAllSpecies());
            return "breeds_form";
        }
        breedsService.saveBreed(breed);
        return "redirect:/breeds";
    }

    @GetMapping("/edit/{id}")
    public String editBreed(@PathVariable Long id, Model model) {
        model.addAttribute("breed", breedsService.getBreedById(id));
        model.addAttribute("breeds", breedsService.getAllBreeds());
        model.addAttribute("species", speciesService.getAllSpecies());
        return "breeds_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBreed(@PathVariable Long id) {
        breedsService.deleteBreed(id);
        return "redirect:/breeds";
    }

    @GetMapping("/search")
    public String searchBreeds(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("breeds", breedsService.searchBreeds(keyword));
        model.addAttribute("breed", new Breeds());
        model.addAttribute("species", speciesService.getAllSpecies());
        model.addAttribute("keyword", keyword);
        return "breeds_form";
    }
}