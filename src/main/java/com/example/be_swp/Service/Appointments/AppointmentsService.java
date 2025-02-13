package com.example.be_swp.Service.Appointments;

import com.example.be_swp.Exceptions.AppointmentNotFoundException;
import com.example.be_swp.Models.Appointments;
import com.example.be_swp.Repository.AppointmentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentsService implements IAppointmentsService {
    private final AppointmentsRepository appointmentsRepository;

    @Override
    public void createAppointment(Appointments appointment) {

    }

    @Override
    public Appointments getAppointmentById(Long id) {
        return appointmentsRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
    }

    @Override
    public void updateAppointmentById(Appointments appointment, Long id) {

    }

    @Override
    public void deleteAppointmentById(Long id) {
        appointmentsRepository.findById(id).ifPresent(appointmentsRepository::delete);
    }

    @Override
    public List<Appointments> getAllAppointments() {
        return appointmentsRepository.findAll();
    }
}
