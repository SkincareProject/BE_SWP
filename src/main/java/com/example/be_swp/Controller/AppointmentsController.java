package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.Appointments.AppointmentsDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.Appointments;
import com.example.be_swp.Models.ExpertOccupiedTimes;
import com.example.be_swp.Repository.AppointmentRepository;
import com.example.be_swp.Repository.ExpertOccupiedTimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
@AllArgsConstructor
public class AppointmentsController {

    private AppointmentRepository _appointmentRepository;
    private ExpertOccupiedTimeRepository _expertOccupiedTimeRepository;


    @GetMapping("/getAll")
    public ApiResponse<?> getAllAppointments() {
        List<Appointments> appointmentsDtoList = _appointmentRepository.findAll();
        String status = "";
        String message = "";

        return new ApiResponse<>(status,appointmentsDtoList,message);
    }

    @PostMapping("/createOrUpdate")
    public ApiResponse<?> createOrUpdate(@RequestBody Appointments body) {
        // Check for expert availability
        List<ExpertOccupiedTimes> expertOccupiedTimes = _expertOccupiedTimeRepository.findOccupiedTimes(
                body.getExpertId().getExpertId(), body.getBookingDate(), body.getStartAt()
        );

        if (expertOccupiedTimes.size() > 0) {
            return new ApiResponse<>("409", null, "expert has another appointment in this time");
        }


        // Check if the appointment exists
        Optional<Appointments> appointments = _appointmentRepository.findById(body.getAppointmentId());
        Appointments toSave = appointments.orElseGet(Appointments::new);

        toSave.setServiceId(body.getServiceId());
        toSave.setUsers(body.getUsers());
        toSave.setStartAt(body.getStartAt());
        toSave.setStatus(0); // Assuming status is intentionally set to 0
        toSave.setExpertId(body.getExpertId());
        toSave.setEndAt(body.getEndAt());

        if(){
        }




        Appointments save = _appointmentRepository.save(toSave);
        return new ApiResponse<>("200", save, null);
    }


}
