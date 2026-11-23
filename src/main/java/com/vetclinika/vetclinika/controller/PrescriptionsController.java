package com.vetclinika.vetclinika.controller;

import com.vetclinika.vetclinika.model.Prescriptions;
import com.vetclinika.vetclinika.service.AppointmentService;
import com.vetclinika.vetclinika.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionsController {

    private final PrescriptionService prescriptionService;
    private final AppointmentService appointmentService;

    public PrescriptionsController(PrescriptionService prescriptionService,
                                   AppointmentService appointmentService) {
        this.prescriptionService = prescriptionService;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public String prescriptionsPage(Model model) {
        model.addAttribute("prescriptions", prescriptionService.getAllPrescriptions());
        model.addAttribute("prescription", new Prescriptions());
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "prescriptions_form";
    }

    @PostMapping("/save")
    public String savePrescription(@Valid @ModelAttribute("prescription") Prescriptions prescription,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("prescriptions", prescriptionService.getAllPrescriptions());
            model.addAttribute("appointments", appointmentService.getAllAppointments());
            return "prescriptions_form";
        }
        prescriptionService.savePrescription(prescription);
        return "redirect:/prescriptions";
    }

    @GetMapping("/edit/{id}")
    public String editPrescription(@PathVariable Long id, Model model) {
        model.addAttribute("prescription", prescriptionService.getPrescriptionById(id));
        model.addAttribute("prescriptions", prescriptionService.getAllPrescriptions());
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "prescriptions_form";
    }

    @GetMapping("/delete/{id}")
    public String deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return "redirect:/prescriptions";
    }

    @GetMapping("/search")
    public String searchPrescriptions(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("prescriptions", prescriptionService.searchPrescriptions(keyword));
        model.addAttribute("prescription", new Prescriptions());
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("keyword", keyword);
        return "prescriptions_form";
    }
}