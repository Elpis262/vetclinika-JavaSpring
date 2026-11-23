package com.vetclinika.vetclinika.controller;

import com.vetclinika.vetclinika.model.Specialities;
import com.vetclinika.vetclinika.service.SpecialityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/specialities")
public class SpecialitiesController {

    private final SpecialityService specialityService;

    public SpecialitiesController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping
    public String specialitiesPage(Model model) {
        model.addAttribute("specialities", specialityService.getAllSpecialities());
        model.addAttribute("speciality", new Specialities());
        return "specialities_form";
    }

    @PostMapping("/save")
    public String saveSpeciality(@Valid @ModelAttribute("speciality") Specialities speciality, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("specialities", specialityService.getAllSpecialities());
            return "specialities_form";
        }
        specialityService.saveSpeciality(speciality);
        return "redirect:/specialities";
    }

    @GetMapping("/edit/{id}")
    public String editSpeciality(@PathVariable Long id, Model model) {
        model.addAttribute("speciality", specialityService.getSpecialityById(id));
        model.addAttribute("specialities", specialityService.getAllSpecialities());
        return "specialities_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteSpeciality(@PathVariable Long id) {
        specialityService.deleteSpeciality(id);
        return "redirect:/specialities";
    }

    @GetMapping("/search")
    public String searchSpecialities(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("specialities", specialityService.searchSpecialities(keyword));
        model.addAttribute("speciality", new Specialities());
        model.addAttribute("keyword", keyword);
        return "specialities_form";
    }
}