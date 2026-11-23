package com.vetclinika.vetclinika.controller;

import com.vetclinika.vetclinika.model.*;
import com.vetclinika.vetclinika.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private final AppointmentService appointmentService;
    private final PetsService petsService;
    private final VetService vetService;
    private final ClinicServiceService clinicServiceService;
    private final UserService userService;
    private final SpeciesService speciesService;

    public BookingController(AppointmentService appointmentService,
                             PetsService petsService,
                             VetService vetService,
                             ClinicServiceService clinicServiceService,
                             UserService userService,
                             SpeciesService speciesService) {
        this.appointmentService = appointmentService;
        this.petsService = petsService;
        this.vetService = vetService;
        this.clinicServiceService = clinicServiceService;
        this.userService = userService;
        this.speciesService = speciesService;
    }

    @GetMapping
    public String bookingPage(Model model) {
        model.addAttribute("vets", vetService.getAllVets());
        model.addAttribute("services", clinicServiceService.getAllServices());
        model.addAttribute("species", speciesService.getAllSpecies());
        return "booking";
    }

    @PostMapping
    public String bookAppointment(
            @RequestParam("petName") String petName,
            @RequestParam("speciesId") Long speciesId,
            @RequestParam("vetId") Long vetId,
            @RequestParam("serviceId") Long serviceId,
            @RequestParam("dateTime") String dateTime,
            RedirectAttributes redirectAttributes) {

        Users currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        Pets pet = petsService.getAllPets().stream()
                .filter(p -> p.getName().equalsIgnoreCase(petName)
                        && p.getOwner() != null
                        && p.getOwner().getId().equals(currentUser.getId()))
                .findFirst()
                .orElse(null);

        if (pet == null) {
            pet = new Pets();
            pet.setName(petName);
            pet.setAge(1);
            pet.setOwner(currentUser);
            pet.setSpecies(speciesService.getSpeciesById(speciesId));
            petsService.savePet(pet);
        }

        Appointments appointment = new Appointments();
        appointment.setPet(pet);
        appointment.setVet(vetService.getVetById(vetId));
        appointment.setService(clinicServiceService.getServiceById(serviceId));
        appointment.setStatus("Запланировано");

        try {
            appointment.setDateTime(LocalDateTime.parse(dateTime));
        } catch (Exception e) {
            appointment.setDateTime(LocalDateTime.now().plusDays(1));
        }

        appointmentService.saveAppointment(appointment);

        redirectAttributes.addFlashAttribute("success", "Вы успешно записаны на приём!");
        return "redirect:/booking/my";
    }

    @GetMapping("/my")
    public String myAppointments(Model model) {
        Users currentUser = getCurrentUser();

        if (currentUser != null) {
            List<Appointments> myAppointments = appointmentService.getAllAppointments().stream()
                    .filter(a -> a.getPet() != null
                            && a.getPet().getOwner() != null
                            && a.getPet().getOwner().getId().equals(currentUser.getId()))
                    .collect(Collectors.toList());
            model.addAttribute("appointments", myAppointments);
        }

        return "my_appointments";
    }

    private Users getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            return userService.getAllUsers().stream()
                    .filter(u -> u.getUsername().equals(auth.getName()))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}