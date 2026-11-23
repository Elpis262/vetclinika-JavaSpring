package com.vetclinika.vetclinika.service;

import com.vetclinika.vetclinika.model.Appointments;
import com.vetclinika.vetclinika.repository.AppointmentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentsRepository appointmentsRepository;

    public AppointmentService(AppointmentsRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }

    public List<Appointments> getAllAppointments() {
        return appointmentsRepository.findAll();
    }

    public Appointments getAppointmentById(Long id) {
        return appointmentsRepository.findById(id).orElse(null);
    }

    public Appointments saveAppointment(Appointments appointment) {
        return appointmentsRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentsRepository.deleteById(id);
    }

    public List<Appointments> searchAppointments(String keyword) {
        return appointmentsRepository.findAll().stream()
                .filter(a ->
                        a.getStatus().toLowerCase().contains(keyword.toLowerCase()) ||
                        (a.getId() != null && a.getId().toString().equals(keyword))
                )
                .toList();
    }
}