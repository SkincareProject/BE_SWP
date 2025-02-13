package com.example.be_swp.Controller;


import com.example.be_swp.Models.Appointments;
import com.example.be_swp.Repository.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentsController {

    @Autowired
    AppointmentsRepository appointmentsRepository;

    @GetMapping("/getAll")
    public List<Appointments> getAllAppointments() {
        return appointmentsRepository.findAll();
    }

    @PostMapping("/save")
    public Appointments saveAppointment(@RequestBody Appointments appointment) {
        return appointmentsRepository.save(appointment);
    }

    @PutMapping("/update/{id}")
    public Appointments updateAppointment(@PathVariable long id ,@RequestBody Appointments appointment) {
        Appointments updatedAppointment = appointmentsRepository.findById(id).get();
        updatedAppointment.setExperts(appointment.getExperts());
        updatedAppointment.setServices(appointment.getServices());
        updatedAppointment.setPayments(appointment.getPayments());
        updatedAppointment.setTotal(appointment.getTotal());
        return appointmentsRepository.save(updatedAppointment);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAppointment(@PathVariable long id) {
        Appointments deletedAppointment = appointmentsRepository.findById(id).get();
        appointmentsRepository.delete(deletedAppointment);
    }
}
