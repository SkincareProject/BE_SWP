package com.example.be_swp.Service;

import com.example.be_swp.DTOs.Appointments.AppointmentUserDTO;
import com.example.be_swp.DTOs.Appointments.AppointmentsDTO;
import com.example.be_swp.Models.*;
import com.example.be_swp.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentsService {
    private final AppointmentRepository _appointmentsRepository;
    private final ServicesRepository _servicesRepository;
    private final UsersRepository _usersRepository;
    private final ExpertRepository _expertRepository;

    public AppointmentsService(UsersRepository usersRepository,ExpertRepository expertRepository,ServicesRepository servicesRepository,AppointmentRepository appointmentRepository) {
        _appointmentsRepository = appointmentRepository;
        _servicesRepository = servicesRepository;
        _usersRepository = usersRepository;
        _expertRepository = expertRepository;
    }

    public List<AppointmentsDTO> findAll(){
        List<Appointments> appointmentsList = _appointmentsRepository.findAll();
        List<AppointmentsDTO> AppointmentsDTOList = new ArrayList<>();
        if (!appointmentsList.isEmpty()){
            for (Appointments appointment: appointmentsList){
                AppointmentsDTO appointmentsDTO = new AppointmentsDTO(appointment.getAppointmentId()
                        ,appointment.getUsers().getId(),appointment.getExperts().getExpertId()
                        ,appointment.getServices().getServiceId(),appointment.getTotal()
                        ,appointment.getStart_at(),appointment.getEnd_at(),appointment.getStatus()
                        ,appointment.getCreated_at(),appointment.getUpdated_at());
                AppointmentsDTOList.add(appointmentsDTO);
            }
        }
        return AppointmentsDTOList;
    }

    public AppointmentsDTO findById(int id){
        AppointmentsDTO appointmentsDTO = new AppointmentsDTO();
        Optional<Appointments> optionalAppointments = _appointmentsRepository.findById(id);
        if (optionalAppointments.isPresent()){
            Appointments appointments = optionalAppointments.get();
            appointmentsDTO = new AppointmentsDTO(appointments.getAppointmentId(),appointments.getUsers().getId()
                    ,appointments.getExperts().getExpertId(),appointments.getServices().getServiceId()
                    ,appointments.getTotal(),appointments.getStart_at(),appointments.getEnd_at()
                    ,appointments.getStatus(),appointments.getCreated_at(),appointments.getUpdated_at());
        }else{
            appointmentsDTO.setAppointmentId(-1);
        }
        return appointmentsDTO;
    }

    public AppointmentsDTO add(AppointmentUserDTO appointmentUserDTO) {
        try {
            // Get existing user from repository
            Optional<Users> userOptional = _usersRepository.findById(appointmentUserDTO.getUsersDTO().getId());
            if (userOptional.isEmpty()) {
                AppointmentsDTO errorDto = new AppointmentsDTO();
                errorDto.setAppointmentId(-1);
                return errorDto;
            }

            // Find expert
            Optional<Experts> expertOptional = _expertRepository.findById(appointmentUserDTO.getExpertsDTO().getExpertId());
            if (expertOptional.isEmpty()) {
                AppointmentsDTO errorDto = new AppointmentsDTO();
                errorDto.setAppointmentId(-2);
                return errorDto;
            }

            // Find service
            Optional<Services> serviceOptional = _servicesRepository.findById(appointmentUserDTO.getServicesDTO().getServiceId());
            if (serviceOptional.isEmpty()) {
                AppointmentsDTO errorDto = new AppointmentsDTO();
                errorDto.setAppointmentId(-3);
                return errorDto;
            }

            // Create new appointment
            Appointments newAppointment = new Appointments();
            newAppointment.setUsers(userOptional.get());
            newAppointment.setExperts(expertOptional.get());
            newAppointment.setServices(serviceOptional.get());
            newAppointment.setTotal(appointmentUserDTO.getAppointmentsDTO().getTotal());
            newAppointment.setStart_at(appointmentUserDTO.getAppointmentsDTO().getStart_at());
            newAppointment.setEnd_at(appointmentUserDTO.getAppointmentsDTO().getEnd_at());
            newAppointment.setStatus(1); // Initial status
            newAppointment.setCreated_at(LocalDateTime.now());
            newAppointment.setUpdated_at(LocalDateTime.now());

            Appointments savedAppointment = _appointmentsRepository.save(newAppointment);

            return new AppointmentsDTO(
                    savedAppointment.getAppointmentId(),
                    savedAppointment.getUsers().getId(),
                    savedAppointment.getExperts().getExpertId(),
                    savedAppointment.getServices().getServiceId(),
                    savedAppointment.getTotal(),
                    savedAppointment.getStart_at(),
                    savedAppointment.getEnd_at(),
                    savedAppointment.getStatus(),
                    savedAppointment.getCreated_at(),
                    savedAppointment.getUpdated_at()
            );

        } catch (Exception e) {
            AppointmentsDTO errorDto = new AppointmentsDTO();
            errorDto.setAppointmentId(-4); // General error
            return errorDto;
        }
    }

    public AppointmentsDTO update(AppointmentsDTO appointmentsDTO, int id){
        Optional<Appointments> optionalAppointments = _appointmentsRepository.findById(id);
        if (optionalAppointments.isPresent()){
            Appointments updateAppointment = new Appointments();
            updateAppointment.setAppointmentId(appointmentsDTO.getAppointmentId());
            updateAppointment.setTotal(appointmentsDTO.getTotal());
            updateAppointment.setStart_at(LocalDateTime.now());
            updateAppointment.setEnd_at(LocalDateTime.now());
            updateAppointment.setStatus(appointmentsDTO.getStatus());
            updateAppointment.setCreated_at(LocalDateTime.now());
            updateAppointment.setUpdated_at(LocalDateTime.now());
            _appointmentsRepository.save(updateAppointment);
        } else {
            appointmentsDTO.setAppointmentId(-1);
        }
        return appointmentsDTO;
    }

    public AppointmentsDTO delete(int id){
        Optional<Appointments> optionalAppointments = _appointmentsRepository.findById(id);
        AppointmentsDTO appointmentsDTO = new AppointmentsDTO();
        if (optionalAppointments.isPresent()){
            Appointments appointments = optionalAppointments.get();
            appointmentsDTO = new AppointmentsDTO(appointments.getAppointmentId(),appointments.getUsers().getId()
                    ,appointments.getExperts().getExpertId(),appointments.getServices().getServiceId()
                    ,appointments.getTotal(),appointments.getStart_at(),appointments.getEnd_at()
                    ,appointments.getStatus(),appointments.getCreated_at(),appointments.getUpdated_at());
            _appointmentsRepository.delete(appointments);
        } else{
            appointmentsDTO.setAppointmentId(-1);
        }
        return appointmentsDTO;
    }

    public List<AppointmentsDTO> findByUserId(int userId) {
        List<Appointments> appointments = _appointmentsRepository.findByUserId(userId);
        List<AppointmentsDTO> appointmentsDTOList = new ArrayList<>();

        for (Appointments appointment : appointments) {
            AppointmentsDTO appointmentDTO = new AppointmentsDTO(
                    appointment.getAppointmentId(),
                    appointment.getUsers().getId(),
                    appointment.getExperts().getExpertId(),
                    appointment.getServices().getServiceId(),
                    appointment.getTotal(),
                    appointment.getStart_at(),
                    appointment.getEnd_at(),
                    appointment.getStatus(),
                    appointment.getCreated_at(),
                    appointment.getUpdated_at()
            );
            appointmentsDTOList.add(appointmentDTO);
        }

        return appointmentsDTOList;
    }
}
