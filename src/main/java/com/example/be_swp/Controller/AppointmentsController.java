package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.Appointments.AppointmentUserDTO;
import com.example.be_swp.DTOs.Appointments.AppointmentsDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Service.AppointmentsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    @GetMapping("/findAllToday")
    public ApiResponse<List<AppointmentsDTO>> findAllToday(){
        List<AppointmentsDTO> appointmentsDTOList = _appointmentsService.findAllToday();

        String status = "";
        String message = "";

        if (appointmentsDTOList.isEmpty()) {
            status = "404";
            message = "Appointment Not Found!";
        } else {
            status = "200";
            message = "Appointment Found!";
        }

        return new ApiResponse<>(status,appointmentsDTOList,message);
    }

    @PostMapping("/add")
    public ApiResponse<AppointmentsDTO> saveAppointment(@RequestBody AppointmentUserDTO appointmentUserDTO) {

        appointmentUserDTO.getAppointmentsDTO().setEnd_at(appointmentUserDTO.getAppointmentsDTO().getEnd_at().plusDays(1));
        appointmentUserDTO.getAppointmentsDTO().setStart_at(appointmentUserDTO.getAppointmentsDTO().getStart_at().plusDays(1));

        AppointmentsDTO appointmentsDTO = _appointmentsService.add(appointmentUserDTO);
        String status;
        String message;

        switch (appointmentsDTO.getAppointmentId()) {
            case -1:
                status = "404";
                message = "User not found!";
                break;
            case -2:
                status = "404";
                message = "Expert not found!";
                break;
            case -3:
                status = "404";
                message = "Service not found!";
                break;
            case -4:
                status = "500";
                message = "Error creating appointment!";
                break;
            case -5:
                status = "400";
                message = "Invalid Time";
                break;
            default:
                status = "200";
                message = "Appointment created successfully!";
        }

        return new ApiResponse<>(status, appointmentsDTO, message);
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

    @GetMapping("/findByUserId/{userId}")
    public ApiResponse<List<AppointmentsDTO>> findByUserId(@PathVariable int userId) {
        List<AppointmentsDTO> appointmentsDTOList = _appointmentsService.findByUserId(userId);
        String status;
        String message;

        if (appointmentsDTOList.isEmpty()) {
            status = "404";
            message = "No appointments found for user ID: " + userId;
        } else {
            status = "200";
            message = "Found appointments for user ID: " + userId;
        }

        return new ApiResponse<>(status, appointmentsDTOList, message);
    }

    @PostMapping("/checkIn/{id}")
    public ApiResponse<AppointmentsDTO> checkInAppointment(@PathVariable int id) {
        AppointmentsDTO appointmentsDTO = _appointmentsService.checkIn(id);

        String status;
        String message;

        switch (appointmentsDTO.getAppointmentId()) {
            case -1:
                status = "404";
                message = "Appointment Not Found!";
                break;
            case -3:
                status = "403";
                message = "This Appointment Is Already Checked In!";
                break;
//            case -4:
//                status = "403";
//                message = "This Appointment Is Already Checked Out!";
//                break;
            default:
                status = "200";
                message = "Check In Successfully! At: " + LocalDateTime.now();
        }

        appointmentsDTO.setAppointmentId(id);
        return new ApiResponse<>(status, appointmentsDTO, message);
    }

    @PostMapping("/checkOut/{id}")
    public ApiResponse<AppointmentsDTO> checkOutAppointment(@PathVariable int id) {
        AppointmentsDTO appointmentsDTO = _appointmentsService.checkOut(id);

        String status;
        String message;

        switch (appointmentsDTO.getAppointmentId()) {
            case -1:
                status = "404";
                message = "Appointment Not Found!";
                break;
            case -3:
                status = "403";
                message = "This Appointment Is Not Checked In Yet!";
                break;
            case -4:
                status = "403";
                message = "This Appointment Is Already Checked Out!";
                break;
            default:
                status = "200";
                message = "Check Out Successfully! At: " + LocalDateTime.now();
        }

        appointmentsDTO.setAppointmentId(id);
        return new ApiResponse<>(status, appointmentsDTO, message);
    }
}
