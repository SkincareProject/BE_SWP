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
                AppointmentsDTO AppointmentsDTO = new AppointmentsDTO(appointment.getAppointmentId()
                        ,appointment.getUsers().getId(),appointment.getExperts().getExpertId()
                        ,appointment.getServices().getServiceId(),appointment.getTotal()
                        ,appointment.getStart_at(),appointment.getEnd_at(),appointment.getStatus()
                        ,appointment.getCreated_at(),appointment.getUpdated_at());
                AppointmentsDTOList.add(AppointmentsDTO);
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

    public AppointmentsDTO add(AppointmentUserDTO appointmentUserDTO){
        Users users = new Users(appointmentUserDTO.getUsersDTO().getUsername(),appointmentUserDTO.getUsersDTO().getPassword()
                ,appointmentUserDTO.getUsersDTO().getFullName(),appointmentUserDTO.getUsersDTO().getEmail()
                ,appointmentUserDTO.getUsersDTO().getPhone(),true,LocalDateTime.now(),LocalDateTime.now());

        Experts experts = new Experts(appointmentUserDTO.getExpertsDTO().getSpecialization()
                ,appointmentUserDTO.getExpertsDTO().getYearOfExperiences()
                ,appointmentUserDTO.getExpertsDTO().getDescription(),1,LocalDateTime.now(),LocalDateTime.now());

        Services services = new Services(appointmentUserDTO.getServicesDTO().getServiceName(),appointmentUserDTO.getServicesDTO().getPrice()
                ,appointmentUserDTO.getServicesDTO().getDescription(),appointmentUserDTO.getServicesDTO().getDuration(),1
                ,appointmentUserDTO.getServicesDTO().getType(),LocalDateTime.now(),LocalDateTime.now());

        Appointments newAppointment = new Appointments();
        newAppointment.setAppointmentId(appointmentUserDTO.getAppointmentsDTO().getAppointmentId());
        newAppointment.setUsers(users);
        newAppointment.setExperts(experts);
        newAppointment.setServices(services);
        newAppointment.setTotal(appointmentUserDTO.getAppointmentsDTO().getTotal());
        newAppointment.setStart_at(LocalDateTime.now());
        newAppointment.setEnd_at(LocalDateTime.now());
        newAppointment.setStatus(appointmentUserDTO.getAppointmentsDTO().getStatus());
        newAppointment.setCreated_at(LocalDateTime.now());
        newAppointment.setUpdated_at(LocalDateTime.now());

        _appointmentsRepository.save(newAppointment);
        return appointmentUserDTO.getAppointmentsDTO();
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
        }else {
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
        }else{
            appointmentsDTO.setAppointmentId(-1);
        }
        return appointmentsDTO;
    }
}
