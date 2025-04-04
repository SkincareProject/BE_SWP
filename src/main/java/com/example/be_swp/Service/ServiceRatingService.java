package com.example.be_swp.Service;

import com.example.be_swp.DTOs.ServiceRatingsDTO;
import com.example.be_swp.Models.*;
import com.example.be_swp.Repository.AppointmentRepository;
import com.example.be_swp.Repository.ServiceRatingsRepository;
import com.example.be_swp.Repository.ServicesRepository;
import com.example.be_swp.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceRatingService {
    private final ServicesRepository _servicesRepository;
    private final AppointmentRepository _appointmentRepository;
    private final UsersRepository _userRepository;
    private final ServiceRatingsRepository _serviceRatingsRepository;


    public ServiceRatingService(ServicesRepository servicesRepository, AppointmentRepository appointmentRepository, UsersRepository userRepository, ServiceRatingsRepository serviceRatingsRepository) {
        _servicesRepository = servicesRepository;
        _appointmentRepository = appointmentRepository;
        _userRepository = userRepository;
        _serviceRatingsRepository = serviceRatingsRepository;
    }

    public List<ServiceRatingsDTO> findAll(){
        List<ServiceRatings> serviceRatingsList = _serviceRatingsRepository.findAll();
        List<ServiceRatingsDTO> serviceRatingsDTOList = new ArrayList<>();
        if (!serviceRatingsList.isEmpty()){
            for (ServiceRatings serviceRatings: serviceRatingsList){
                ServiceRatingsDTO serviceRatingsDTO = new ServiceRatingsDTO(serviceRatings.getServiceRatingId()
                        ,serviceRatings.getAppointments().getAppointmentId(),serviceRatings.getUsers().getId()
                        ,serviceRatings.getServices().getServiceId(),serviceRatings.getRating(),serviceRatings.getFeedback()
                        ,serviceRatings.getStatus(),serviceRatings.getCreated_at(),serviceRatings.getUpdated_at());
                serviceRatingsDTOList.add(serviceRatingsDTO);
            }
        }
        return serviceRatingsDTOList;
    }

    public ServiceRatingsDTO findById(int id){
        ServiceRatingsDTO serviceRatingsDTO = new ServiceRatingsDTO();
        Optional<ServiceRatings> optionalServiceRatings = _serviceRatingsRepository.findById(id);
        if (optionalServiceRatings.isEmpty()){
            serviceRatingsDTO.setServicesId(-1);
        } else {
            ServiceRatings serviceRatings = optionalServiceRatings.get();
            serviceRatingsDTO = new ServiceRatingsDTO(serviceRatings.getServiceRatingId()
                    ,serviceRatings.getAppointments().getAppointmentId(),serviceRatings.getUsers().getId()
                    ,serviceRatings.getServices().getServiceId(),serviceRatings.getRating(),serviceRatings.getFeedback()
                    ,serviceRatings.getStatus(),serviceRatings.getCreated_at(),serviceRatings.getUpdated_at());
        }
        return serviceRatingsDTO;
    }

    public ServiceRatingsDTO findByServiceId(int id){
        ServiceRatingsDTO serviceRatingsDTO = new ServiceRatingsDTO();
        Optional<ServiceRatings> optionalServiceRatings = _serviceRatingsRepository.findByServiceId(id);
        if (optionalServiceRatings.isEmpty()){
            serviceRatingsDTO.setServicesId(-1);
        } else {
            ServiceRatings serviceRatings = optionalServiceRatings.get();
            serviceRatingsDTO = new ServiceRatingsDTO(serviceRatings.getServiceRatingId()
                    ,serviceRatings.getAppointments().getAppointmentId(),serviceRatings.getUsers().getId()
                    ,serviceRatings.getServices().getServiceId(),serviceRatings.getRating(),serviceRatings.getFeedback()
                    ,serviceRatings.getStatus(),serviceRatings.getCreated_at(),serviceRatings.getUpdated_at());
        }
        return serviceRatingsDTO;
    }

    public ServiceRatingsDTO add(ServiceRatingsDTO serviceRatingsDTO){
        Optional<Users> optionalUsers = _userRepository.findById(serviceRatingsDTO.getUsersId());
        Optional<Services> optionalServices = _servicesRepository.findById(serviceRatingsDTO.getServicesId());
        Optional<Appointments> optionalAppointments = _appointmentRepository.findById(serviceRatingsDTO.getAppointmentsId());

        if (optionalUsers.isEmpty()){
            serviceRatingsDTO.setServicesId(-1);
        } else if(optionalServices.isEmpty()){
            serviceRatingsDTO.setServicesId(-2);
        } else if(optionalAppointments.isEmpty()) {
            serviceRatingsDTO.setServicesId(-3);
        } else if (serviceRatingsDTO.getRating() < 0 || serviceRatingsDTO.getRating() > 5){
            serviceRatingsDTO.setServicesId(-4);
        } else{
            Users users = optionalUsers.get();
            Services services = optionalServices.get();
            Appointments appointments = optionalAppointments.get();

            ServiceRatings serviceRatings = new ServiceRatings(appointments, users, services, serviceRatingsDTO.getRating(), serviceRatingsDTO.getFeedback(), 1, LocalDateTime.now(), LocalDateTime.now());
            users.getServiceRatingsList().add(serviceRatings);
            services.getServiceRatingsList().add(serviceRatings);
            appointments.setServiceRatings(serviceRatings);

            _serviceRatingsRepository.save(serviceRatings);
            serviceRatingsDTO.setServiceRatingId(serviceRatings.getServiceRatingId());
        }
        return serviceRatingsDTO;
    }

    public ServiceRatingsDTO update(ServiceRatingsDTO serviceRatingsDTO, int id){
        Optional<ServiceRatings> optionalServiceRatings = _serviceRatingsRepository.findById(id);

        if (optionalServiceRatings.isEmpty()){
            serviceRatingsDTO.setServiceRatingId(-1);
        } else if (serviceRatingsDTO.getRating() < 0 || serviceRatingsDTO.getRating() > 5) {
            serviceRatingsDTO.setServiceRatingId(-4);
        } else {
            ServiceRatings serviceRatings = optionalServiceRatings.get();

            serviceRatings.setRating(serviceRatingsDTO.getRating());
            serviceRatings.setFeedback(serviceRatingsDTO.getFeedback());
            serviceRatings.setUpdated_at(LocalDateTime.now());

            _serviceRatingsRepository.save(serviceRatings);
        }
        return serviceRatingsDTO;
    }


    public ServiceRatingsDTO delete(int id){
        ServiceRatingsDTO serviceRatingsDTO = new ServiceRatingsDTO();
        Optional<ServiceRatings> optionalServiceRatings = _serviceRatingsRepository.findById(id);

        if (optionalServiceRatings.isEmpty()){
            serviceRatingsDTO.setServiceRatingId(-1);
        }else {
            ServiceRatings serviceRatings = optionalServiceRatings.get();

            serviceRatingsDTO = new ServiceRatingsDTO();
            _serviceRatingsRepository.delete(serviceRatings);
        }
        return serviceRatingsDTO;
    }
}
