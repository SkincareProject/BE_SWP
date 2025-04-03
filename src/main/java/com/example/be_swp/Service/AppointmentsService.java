package com.example.be_swp.Service;

import com.example.be_swp.DTOs.Appointments.AppointmentUserDTO;
import com.example.be_swp.DTOs.Appointments.AppointmentsDTO;
import com.example.be_swp.Models.*;
import com.example.be_swp.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentsService {
    private final AppointmentRepository _appointmentsRepository;
    private final ServicesRepository _servicesRepository;
    private final UsersRepository _usersRepository;
    private final ExpertRepository _expertRepository;
    private final ExpertOccupiedTimeRepository _expertOccupiedTimeRepository;

    public AppointmentsService(UsersRepository usersRepository,ExpertRepository expertRepository,ServicesRepository servicesRepository,AppointmentRepository appointmentRepository,ExpertOccupiedTimeRepository expertOccupiedTimeRepository) {
        _appointmentsRepository = appointmentRepository;
        _servicesRepository = servicesRepository;
        _usersRepository = usersRepository;
        _expertRepository = expertRepository;
        _expertOccupiedTimeRepository = expertOccupiedTimeRepository;
    }

    public List<AppointmentsDTO> findAll(){
        List<Appointments> appointmentsList = _appointmentsRepository.findAll();
        List<AppointmentsDTO> AppointmentsDTOList = new ArrayList<>();
        if (!appointmentsList.isEmpty()){
            for (Appointments appointment: appointmentsList){
                AppointmentsDTO appointmentsDTO = new AppointmentsDTO(appointment.getAppointmentId()
                        ,appointment.getUsers().getId()
                        ,appointment.getExperts().getExpertId()
                        ,appointment.getExperts().getUsers().getFullName()
                        ,appointment.getServices().getServiceId()
                        ,appointment.getPayments() == null ? 0 : appointment.getPayments().getStatus()
                        ,appointment.getTotal()
                        ,appointment.getStart_at()
                        ,appointment.getEnd_at()
                        ,appointment.getStatus()
                        ,appointment.getCreated_at()
                        ,appointment.getUpdated_at());
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
            appointmentsDTO = new AppointmentsDTO(appointments.getAppointmentId()
                    ,appointments.getUsers().getId()
                    ,appointments.getExperts().getExpertId()
                    ,appointments.getExperts().getUsers().getFullName()
                    ,appointments.getServices().getServiceId()
                    ,appointments.getPayments() == null ? 0 : appointments.getPayments().getStatus()
                    ,appointments.getTotal()
                    ,appointments.getStart_at()
                    ,appointments.getEnd_at()
                    ,appointments.getStatus()
                    ,appointments.getCreated_at()
                    ,appointments.getUpdated_at());
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

            Optional<ExpertOccupiedTimes> optionalExpertOccupiedTimes = _expertOccupiedTimeRepository.findByExpertIdWithTime(expertOptional.get().getExpertId(),appointmentUserDTO.getAppointmentsDTO().getStart_at().toLocalDate(), appointmentUserDTO.getAppointmentsDTO().getStart_at().toLocalTime(), appointmentUserDTO.getAppointmentsDTO().getEnd_at().toLocalTime());
            if (optionalExpertOccupiedTimes.isEmpty()) {
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

                ExpertOccupiedTimes expertOccupiedTimes = new ExpertOccupiedTimes();
                expertOccupiedTimes.setExperts(expertOptional.get());
                expertOccupiedTimes.setStartAt(savedAppointment.getStart_at().toLocalTime());
                expertOccupiedTimes.setEndAt(savedAppointment.getEnd_at().toLocalTime());
                expertOccupiedTimes.setDate(savedAppointment.getStart_at().toLocalDate());
                expertOccupiedTimes.setStatus(1);
                expertOccupiedTimes.setCreated_at(LocalDateTime.now());
                expertOccupiedTimes.setUpdated_at(LocalDateTime.now());

                _expertOccupiedTimeRepository.save(expertOccupiedTimes);

                return new AppointmentsDTO(
                        savedAppointment.getAppointmentId(),
                        savedAppointment.getUsers().getId(),
                        savedAppointment.getExperts().getExpertId(),
                        savedAppointment.getExperts().getUsers().getFullName(),
                        savedAppointment.getServices().getServiceId(),
                        savedAppointment.getPayments() == null ? 0 : savedAppointment.getPayments().getStatus(),
                        savedAppointment.getTotal(),
                        savedAppointment.getStart_at(),
                        savedAppointment.getEnd_at(),
                        savedAppointment.getStatus(),
                        savedAppointment.getCreated_at(),
                        savedAppointment.getUpdated_at()
                );
            }else{
                AppointmentsDTO errorDto = new AppointmentsDTO();
                errorDto.setAppointmentId(-5);
                return errorDto;
            }

        } catch (Exception e) {
            AppointmentsDTO errorDto = new AppointmentsDTO();
            errorDto.setAppointmentId(-4); // General error
            return errorDto;
        }
    }

    public AppointmentsDTO update(AppointmentsDTO appointmentsDTO, int id){
        Optional<Appointments> optionalAppointments = _appointmentsRepository.findById(id);
        if (optionalAppointments.isPresent()){
            Appointments updateAppointment = optionalAppointments.get();
            updateAppointment.setTotal(appointmentsDTO.getTotal());
            updateAppointment.setStart_at(appointmentsDTO.getStart_at());
            updateAppointment.setEnd_at(appointmentsDTO.getEnd_at());
            updateAppointment.setStatus(appointmentsDTO.getStatus());
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

            appointments.setStatus(0);
            _appointmentsRepository.save(appointments);

            Optional<ExpertOccupiedTimes> optionalExpertOccupiedTimes = _expertOccupiedTimeRepository.findByAppointment(appointments.getExperts().getExpertId(),appointments.getStart_at().toLocalTime(), appointments.getEnd_at().toLocalTime(), appointments.getStart_at().toLocalDate());

            if (optionalExpertOccupiedTimes.isPresent()) {
                ExpertOccupiedTimes expertOccupiedTimes = optionalExpertOccupiedTimes.get();
                expertOccupiedTimes.setStatus(0);
                _expertOccupiedTimeRepository.save(expertOccupiedTimes);
            }

            appointmentsDTO = new AppointmentsDTO(appointments.getAppointmentId()
                    ,appointments.getUsers().getId()
                    ,appointments.getExperts().getExpertId()
                    ,appointments.getExperts().getUsers().getFullName()
                    ,appointments.getServices().getServiceId()
                    ,appointments.getPayments() == null ? 0 : appointments.getPayments().getStatus()
                    ,appointments.getTotal()
                    ,appointments.getStart_at()
                    ,appointments.getEnd_at()
                    ,appointments.getStatus()
                    ,appointments.getCreated_at()
                    ,appointments.getUpdated_at());


        } else{
            appointmentsDTO.setAppointmentId(-1);
        }
        return appointmentsDTO;
    }

    public List<AppointmentsDTO> findAllToday(){
        List<Appointments> appointmentsList = _appointmentsRepository.findAllToday(LocalDate.now());
        List<AppointmentsDTO> AppointmentsDTOList = new ArrayList<>();
        if (!appointmentsList.isEmpty()){
            for (Appointments appointment: appointmentsList){
                AppointmentsDTO appointmentsDTO = new AppointmentsDTO(appointment.getAppointmentId()
                        ,appointment.getUsers().getId()
                        ,appointment.getExperts().getExpertId()
                        ,appointment.getExperts().getUsers().getFullName()
                        ,appointment.getServices().getServiceId()
                        ,appointment.getPayments() == null ? 0 : appointment.getPayments().getStatus()
                        ,appointment.getTotal()
                        ,appointment.getStart_at()
                        ,appointment.getEnd_at()
                        ,appointment.getStatus()
                        ,appointment.getCreated_at()
                        ,appointment.getUpdated_at());
                AppointmentsDTOList.add(appointmentsDTO);
            }
        }
        return AppointmentsDTOList;
    }

    public List<AppointmentsDTO> findByUserId(int userId) {
        List<Appointments> appointments = _appointmentsRepository.findByUserId(userId);
        List<AppointmentsDTO> appointmentsDTOList = new ArrayList<>();

        for (Appointments appointment : appointments) {
            AppointmentsDTO appointmentDTO = new AppointmentsDTO(
                    appointment.getAppointmentId()
                    ,appointment.getUsers().getId()
                    ,appointment.getExperts().getExpertId()
                    ,appointment.getExperts().getUsers().getFullName()
                    ,appointment.getServices().getServiceId()
                    ,appointment.getPayments() == null ? 0 : appointment.getPayments().getStatus()
                    ,appointment.getTotal()
                    ,appointment.getStart_at()
                    ,appointment.getEnd_at()
                    ,appointment.getStatus()
                    ,appointment.getCreated_at()
                    ,appointment.getUpdated_at()
            );
            appointmentsDTOList.add(appointmentDTO);
        }

        return appointmentsDTOList;
    }

    public AppointmentsDTO checkIn(int id) {
        AppointmentsDTO appointmentsDTO = new AppointmentsDTO();

        Optional<Appointments> optionalAppointment = _appointmentsRepository.findById(id);

        if (optionalAppointment.isEmpty()) {
            appointmentsDTO.setAppointmentId(-1);
            return appointmentsDTO;
        }

        Appointments appointment = optionalAppointment.get();

        // Check if already checked in or checked out
        if (appointment.getStatus() == 3) {
            appointmentsDTO.setAppointmentId(-3);
            return appointmentsDTO;
        }

        if (appointment.getStatus() == 4) {
            appointmentsDTO.setAppointmentId(-4);
            return appointmentsDTO;
        }

        // Perform check-in
        appointment.setStatus(3);
        appointment.setUpdated_at(LocalDateTime.now());
        _appointmentsRepository.save(appointment);

        return appointmentsDTO;
    }

    public AppointmentsDTO checkOut(int id) {
        AppointmentsDTO appointmentsDTO = new AppointmentsDTO();

        Optional<Appointments> optionalAppointment = _appointmentsRepository.findById(id);

        if (optionalAppointment.isEmpty()) {
            appointmentsDTO.setAppointmentId(-1);
            return appointmentsDTO;
        }

        Appointments appointment = optionalAppointment.get();

        // Check if not checked in
        if (appointment.getStatus() != 3) {
            appointmentsDTO.setAppointmentId(-3);
            return appointmentsDTO;
        }

        // Check if already checked out
        if (appointment.getStatus() == 4) {
            appointmentsDTO.setAppointmentId(-4);
            return appointmentsDTO;
        }

        // Perform check-out
        appointment.setStatus(4);
        appointment.setUpdated_at(LocalDateTime.now());
        _appointmentsRepository.save(appointment);

        return appointmentsDTO;
    }
}
