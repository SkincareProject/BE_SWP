package com.example.be_swp.mapper.appointment;


import com.example.be_swp.DTOs.Appointments.AppointmentsDTO;
import com.example.be_swp.DTOs.ExpertsDTO;
import com.example.be_swp.Models.*;
import com.example.be_swp.Repository.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Builder
@Data
@AllArgsConstructor
@Component
public class AppointmentMapper {


    private final ExpertRepository expertRepository;
    private ServicesRepository servicesRepository;
    private AppointmentRepository appointmentRepository;
    private UsersRepository usersRepository;
    private ExpertOccupiedTimeRepository    expertOccupiedTimeRepository;

    public AppointmentsDTO mapToDTO (Appointments appointments) {
        AppointmentsDTO appointmentsDTO = new AppointmentsDTO();

        Services services = servicesRepository.findByServiceId(appointments.getServiceId());
        if(services==null){
            services = new Services();
        }

        Experts expert=expertRepository.findByExpertId(appointments.getExpertId());
        Optional<Users> users = usersRepository.findById(expert.getUsers().getId());


        ExpertOccupiedTimes expertOccupiedTimes = expertOccupiedTimeRepository.findByAppointmentId(appointments.getAppointmentId());

        // Bypass nếu expertOccupiedTimes là null
        Long expertScheduleId = (expertOccupiedTimes != null) ? expertOccupiedTimes.getExpertOccupiedTimeId() : 0;

        return appointmentsDTO.builder()
                .serviceName(services.getServiceName()!=null ?services.getServiceName():"")
                .expertsName(users.map(Users::getFullName).orElse("N/A")) // Tránh lỗi nếu user không tồn tại
                .total(appointments.getTotal())
                .serviceId(services.getServiceId())
                .startAt(appointments.getStartAt())
                .endAt(appointments.getEndAt())
                .date(appointments.getBookingDate())
                .status(appointments.getStatus())
                .expertId(appointments.getExpertId())
                .expertScheduleId(expertScheduleId)
                .appointmentId(appointments.getAppointmentId())
                .duration(services.getDuration())
                .build();
    }


    public ExpertsDTO mapToExpertDTO(Appointments appointments) {
        ExpertsDTO expertsDTO = new ExpertsDTO();
        Services services = servicesRepository.findByServiceId(appointments.getServiceId());
        Optional<Users> users = usersRepository.findById(appointments.getUserId());

        return expertsDTO.builder()
                .serviceName(services.getServiceName())
                .startAt(appointments.getStartAt())
                .endAt(appointments.getEndAt())
                .date(appointments.getBookingDate())
                .duration(services.getDuration())
                .status(appointments.getStatus())
                .customerName(users.get().getFullName())
                .total(appointments.getTotal())
                .imageBase64(services.getImage())
                .appointmentId(appointments.getAppointmentId())



                .build();


    }




}
