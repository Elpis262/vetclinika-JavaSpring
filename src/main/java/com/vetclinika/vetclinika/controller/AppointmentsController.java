package com.vetclinika.vetclinika.controller;

import com.vetclinika.vetclinika.model.Appointments;
import com.vetclinika.vetclinika.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentsController {

    private final AppointmentService appointmentService;
    private final PetsService petsService;
    private final VetService vetService;
    private final ClinicServiceService clinicServiceService;

    public AppointmentsController(AppointmentService appointmentService,
                                  PetsService petsService,
                                  VetService vetService,
                                  ClinicServiceService clinicServiceService) {
        this.appointmentService = appointmentService;
        this.petsService = petsService;
        this.vetService = vetService;
        this.clinicServiceService = clinicServiceService;
    }

    @GetMapping
    public String appointmentsPage(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("appointment", new Appointments());
        model.addAttribute("pets", petsService.getAllPets());
        model.addAttribute("vets", vetService.getAllVets());
        model.addAttribute("services", clinicServiceService.getAllServices());
        return "appointments_form";
    }

    @PostMapping("/save")
    public String saveAppointment(@Valid @ModelAttribute("appointment") Appointments appointment,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("appointments", appointmentService.getAllAppointments());
            model.addAttribute("pets", petsService.getAllPets());
            model.addAttribute("vets", vetService.getAllVets());
            model.addAttribute("services", clinicServiceService.getAllServices());
            return "appointments_form";
        }
        appointmentService.saveAppointment(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    public String editAppointment(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getAppointmentById(id));
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("pets", petsService.getAllPets());
        model.addAttribute("vets", vetService.getAllVets());
        model.addAttribute("services", clinicServiceService.getAllServices());
        return "appointments_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }

    @GetMapping("/search")
    public String searchAppointments(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("appointments", appointmentService.searchAppointments(keyword));
        model.addAttribute("appointment", new Appointments());
        model.addAttribute("pets", petsService.getAllPets());
        model.addAttribute("vets", vetService.getAllVets());
        model.addAttribute("services", clinicServiceService.getAllServices());
        model.addAttribute("keyword", keyword);
        return "appointments_form";
    }
}