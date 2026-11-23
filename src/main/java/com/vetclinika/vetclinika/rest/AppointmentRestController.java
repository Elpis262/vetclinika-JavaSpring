package com.vetclinika.vetclinika.rest;

import com.vetclinika.vetclinika.model.Appointments;
import com.vetclinika.vetclinika.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentRestController {

    private final AppointmentService appointmentService;

    public AppointmentRestController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<Appointments> getAll() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointments> getById(@PathVariable Long id) {
        Appointments appointment = appointmentService.getAppointmentById(id);
        if (appointment == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(appointment);
    }

    @PostMapping
    public Appointments create(@RequestBody Appointments appointment) {
        return appointmentService.saveAppointment(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointments> update(@PathVariable Long id, @RequestBody Appointments appointment) {
        Appointments existing = appointmentService.getAppointmentById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        appointment.setId(id);
        return ResponseEntity.ok(appointmentService.saveAppointment(appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}