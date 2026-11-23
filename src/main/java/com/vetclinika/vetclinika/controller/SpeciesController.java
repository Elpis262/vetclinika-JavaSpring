package com.vetclinika.vetclinika.controller;

import com.vetclinika.vetclinika.model.Species;
import com.vetclinika.vetclinika.service.SpeciesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/species")
public class SpeciesController {

    private final SpeciesService speciesService;

    public SpeciesController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @GetMapping
    public String speciesPage(Model model) {
        model.addAttribute("species", speciesService.getAllSpecies());
        model.addAttribute("specy", new Species());
        return "species_form";
    }

    @PostMapping("/save")
    public String saveSpecies(@Valid @ModelAttribute("specy") Species specy, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("species", speciesService.getAllSpecies());
            return "species_form";
        }
        speciesService.saveSpecies(specy);
        return "redirect:/species";
    }

    @GetMapping("/edit/{id}")
    public String editSpecies(@PathVariable Long id, Model model) {
        model.addAttribute("specy", speciesService.getSpeciesById(id));
        model.addAttribute("species", speciesService.getAllSpecies());
        return "species_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteSpecies(@PathVariable Long id) {
        speciesService.deleteSpecies(id);
        return "redirect:/species";
    }

    @GetMapping("/search")
    public String searchSpecies(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("species", speciesService.searchSpecies(keyword));
        model.addAttribute("specy", new Species());
        model.addAttribute("keyword", keyword);
        return "species_form";
    }
}