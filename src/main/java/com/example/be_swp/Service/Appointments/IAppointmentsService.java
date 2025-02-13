package com.example.be_swp.Service.Appointments;

import com.example.be_swp.Models.Appointments;

import java.util.List;

public interface IAppointmentsService {
    void createAppointment(Appointments appointment);
    Appointments getAppointmentById(Long id);
    void updateAppointmentById(Appointments appointment, Long id);
    void deleteAppointmentById(Long id);
    List<Appointments> getAllAppointments();

}
