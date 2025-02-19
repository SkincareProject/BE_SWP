package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.Appointments.AppointmentUserDTO;
import com.example.be_swp.DTOs.Appointments.AppointmentsDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Repository.AppointmentRepository;
import com.example.be_swp.Service.AppointmentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentsController {

    private final AppointmentsService _appointmentsService;

    public AppointmentsController(AppointmentsService _appointmentsService) {
        this._appointmentsService = _appointmentsService;
    }

    @GetMapping("/getAll")
    public ApiResponse<List<AppointmentsDTO>> getAllAppointments() {
        List<AppointmentsDTO> appointmentsDtoList = _appointmentsService.findAll();
        String status = "";
        String message = "";
        if (appointmentsDtoList.isEmpty()) {
            status = "404";
            message = "Appointment Not Found!";
        } else {
            status = "200";
            message = "Appointment Found!";
        }
        return new ApiResponse<>(status,appointmentsDtoList,message);
    }

    @GetMapping("/findById/{id}")
    public ApiResponse<AppointmentsDTO> findById(@PathVariable int id){
        AppointmentsDTO appointmentsDTO = _appointmentsService.findById(id);

        String status = "";
        String message = "";

        if (appointmentsDTO.getAppointmentId() == -1){
            status = "404";
            message = "Appointment Not Found!";
        } else {
            status = "200";
            message = "Appointment Found!";
        }

        appointmentsDTO.setAppointmentId(id);

        return new ApiResponse<>(status,appointmentsDTO,message);
    }

    @PostMapping("/add")
    public ApiResponse<AppointmentsDTO> saveAppointment(@RequestBody AppointmentUserDTO appointmentUserDTO) {
        AppointmentsDTO appointmentsDTO = _appointmentsService.add(appointmentUserDTO);
        String status = "200";
        String message = "Saved Appointment!";
        return new ApiResponse<>(status,appointmentsDTO,message);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<AppointmentsDTO> updateAppointment(@PathVariable int id,@RequestBody AppointmentsDTO appointmentsDTO) {
        appointmentsDTO = _appointmentsService.update(appointmentsDTO,id);
        String status = "";
        String message = "";

        if (appointmentsDTO.getAppointmentId() == -1){
            status = "404";
            message = "Appointment Not Found!";
        } else {
            status = "200";
            message = "Appointment Updated!";
        }

        appointmentsDTO.setAppointmentId(id);

        return new ApiResponse<>(status,appointmentsDTO,message);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<AppointmentsDTO> deleteAppointment(@PathVariable int id) {
        AppointmentsDTO appointmentsDTO = _appointmentsService.delete(id);
        String status = "";
        String message = "";

        if (appointmentsDTO.getAppointmentId() == -1){
            status = "404";
            message = "Appointment Not Found!";
        } else {
            status = "200";
            message = "Appointment Deleted!";
        }
        appointmentsDTO.setAppointmentId(id);

        return new ApiResponse<>(status,appointmentsDTO,message);

    }
}
