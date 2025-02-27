package com.example.be_swp.Service;

import com.example.be_swp.DTOs.ExpertRatingDTO;
import com.example.be_swp.Models.Appointments;
import com.example.be_swp.Models.ExpertRatings;
import com.example.be_swp.Models.Experts;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.AppointmentRepository;
import com.example.be_swp.Repository.ExpertRatingsRepository;
import com.example.be_swp.Repository.ExpertRepository;
import com.example.be_swp.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertRatingService {

    private final ExpertRatingsRepository _expertRatingsRepository;
    private final ExpertRepository _expertRepository;
    private final UsersRepository _usersRepository;
    private final AppointmentRepository _appointmentRepository;

    public ExpertRatingService(ExpertRatingsRepository _expertRatingsRepository, ExpertRepository _expertRepository, UsersRepository _usersRepository, AppointmentRepository _appointmentRepository) {
        this._expertRatingsRepository = _expertRatingsRepository;
        this._expertRepository = _expertRepository;
        this._usersRepository = _usersRepository;
        this._appointmentRepository = _appointmentRepository;
    }

    public List<ExpertRatingDTO> findAll(){
        List<ExpertRatingDTO> expertRatingDTOList = new ArrayList<>();
        List<ExpertRatings> expertRatingsList = _expertRatingsRepository.findAll();

        if (!expertRatingsList.isEmpty()){
            for (ExpertRatings expertRatings: expertRatingsList){
                ExpertRatingDTO expertRatingDTO = new ExpertRatingDTO(expertRatings.getExpertRatingId(), expertRatings.getAppointments().getAppointmentId(), expertRatings.getUsers().getId(), expertRatings.getExperts().getExpertId(), expertRatings.getRating(), expertRatings.getFeedback(), expertRatings.getStatus(), expertRatings.getCreated_at(), expertRatings.getUpdated_at());
                expertRatingDTOList.add(expertRatingDTO);
            }
        }

        return expertRatingDTOList;
    }

    public ExpertRatingDTO findById(int id){
        ExpertRatingDTO expertRatingDTO = new ExpertRatingDTO();

        Optional<ExpertRatings> optionalExpertRatings = _expertRatingsRepository.findById(id);

        if (optionalExpertRatings.isEmpty()){
            expertRatingDTO.setExpertRatingId(-1);
        }else {
            ExpertRatings expertRatings = optionalExpertRatings.get();
            expertRatingDTO = new ExpertRatingDTO(expertRatings.getExpertRatingId(), expertRatings.getAppointments().getAppointmentId(), expertRatings.getUsers().getId(), expertRatings.getExperts().getExpertId(), expertRatings.getRating(), expertRatings.getFeedback(), expertRatings.getStatus(), expertRatings.getCreated_at(), expertRatings.getUpdated_at());
        }

        return expertRatingDTO;
    }

    public ExpertRatingDTO findByExpertId(int id){
        ExpertRatingDTO expertRatingDTO = new ExpertRatingDTO();

        Optional<ExpertRatings> optionalExpertRatings = _expertRatingsRepository.findByExpertId(id);

        if (optionalExpertRatings.isEmpty()){
            expertRatingDTO.setExpertRatingId(-1);
        }else {
            ExpertRatings expertRatings = optionalExpertRatings.get();
            expertRatingDTO = new ExpertRatingDTO(expertRatings.getExpertRatingId(), expertRatings.getAppointments().getAppointmentId(), expertRatings.getUsers().getId(), expertRatings.getExperts().getExpertId(), expertRatings.getRating(), expertRatings.getFeedback(), expertRatings.getStatus(), expertRatings.getCreated_at(), expertRatings.getUpdated_at());
        }

        return expertRatingDTO;
    }

    public ExpertRatingDTO add(ExpertRatingDTO expertRatingDTO){

        Optional<Users> optionalCustomer = _usersRepository.findById(expertRatingDTO.getCustomerId());
        Optional<Experts> optionalExperts = _expertRepository.findById(expertRatingDTO.getExpertId());
        Optional<Appointments> optionalAppointments = _appointmentRepository.findById(expertRatingDTO.getAppointmentId());

        if (optionalCustomer.isEmpty()){
            expertRatingDTO.setExpertRatingId(-1);
        }else if(optionalExperts.isEmpty()){
            expertRatingDTO.setExpertRatingId(-2);
        }else if(optionalAppointments.isEmpty()) {
            expertRatingDTO.setExpertRatingId(-3);
        }else if (expertRatingDTO.getRating() < 0 || expertRatingDTO.getRating() > 5){
            expertRatingDTO.setExpertRatingId(-4);
        }else{
            Users customer = optionalCustomer.get();
            Experts experts = optionalExperts.get();
            Appointments appointments = optionalAppointments.get();

            ExpertRatings expertRatings = new ExpertRatings(appointments, experts, customer, expertRatingDTO.getRating(), expertRatingDTO.getFeedback(), 1, LocalDateTime.now(), LocalDateTime.now());
            customer.getExpertRatingsList().add(expertRatings);
            experts.getExpertRatingsList().add(expertRatings);
            appointments.setExpertRatings(expertRatings);

            _expertRatingsRepository.save(expertRatings);

            expertRatingDTO.setExpertRatingId(expertRatings.getExpertRatingId());
        }

        return expertRatingDTO;
    }

    public ExpertRatingDTO update(ExpertRatingDTO expertRatingDTO, int id){
        Optional<ExpertRatings> optionalExpertRatings = _expertRatingsRepository.findById(id);

        if (optionalExpertRatings.isEmpty()){
            expertRatingDTO.setExpertRatingId(-1);
        } else if (expertRatingDTO.getRating() < 0 || expertRatingDTO.getRating() > 5) {
            expertRatingDTO.setExpertRatingId(-4);
        } else {
            ExpertRatings expertRatings = optionalExpertRatings.get();

            expertRatings.setRating(expertRatingDTO.getRating());
            expertRatings.setFeedback(expertRatingDTO.getFeedback());
            expertRatings.setUpdated_at(LocalDateTime.now());

            _expertRatingsRepository.save(expertRatings);

        }

        return expertRatingDTO;
    }


    public ExpertRatingDTO delete(int id){
        ExpertRatingDTO expertRatingDTO = new ExpertRatingDTO();

        Optional<ExpertRatings> optionalExpertRatings = _expertRatingsRepository.findById(id);

        if (optionalExpertRatings.isEmpty()){
            expertRatingDTO.setExpertRatingId(-1);
        }else {
            ExpertRatings expertRatings = optionalExpertRatings.get();

            expertRatingDTO = new ExpertRatingDTO(expertRatings.getExpertRatingId(), expertRatings.getAppointments().getAppointmentId(), expertRatings.getUsers().getId(), expertRatings.getExperts().getExpertId(), expertRatings.getRating(), expertRatings.getFeedback(), expertRatings.getStatus(), expertRatings.getCreated_at(), expertRatings.getUpdated_at());

            _expertRatingsRepository.delete(expertRatings);
        }

        return expertRatingDTO;
    }

}
