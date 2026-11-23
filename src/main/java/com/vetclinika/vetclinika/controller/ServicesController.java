package com.vetclinika.vetclinika.controller;

import com.vetclinika.vetclinika.model.Services;
import com.vetclinika.vetclinika.service.ClinicServiceService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/services")
public class ServicesController {

    private final ClinicServiceService clinicServiceService;

    public ServicesController(ClinicServiceService clinicServiceService) {
        this.clinicServiceService = clinicServiceService;
    }

    @GetMapping
    public String servicesPage(Model model) {
        model.addAttribute("services", clinicServiceService.getAllServices());
        model.addAttribute("service", new Services());
        return "services_form";
    }

    @PostMapping("/save")
    public String saveService(@Valid @ModelAttribute("service") Services service, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("services", clinicServiceService.getAllServices());
            return "services_form";
        }
        clinicServiceService.saveService(service);
        return "redirect:/services";
    }

    @GetMapping("/edit/{id}")
    public String editService(@PathVariable Long id, Model model) {
        model.addAttribute("service", clinicServiceService.getServiceById(id));
        model.addAttribute("services", clinicServiceService.getAllServices());
        return "services_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        clinicServiceService.deleteService(id);
        return "redirect:/services";
    }

    @GetMapping("/search")
    public String searchServices(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("services", clinicServiceService.searchServices(keyword));
        model.addAttribute("service", new Services());
        model.addAttribute("keyword", keyword);
        return "services_form";
    }
}