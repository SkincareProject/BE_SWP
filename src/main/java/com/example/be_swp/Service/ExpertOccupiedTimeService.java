package com.example.be_swp.Service;

import com.example.be_swp.DTOs.ExpertOccupiedTimesDTO;
import com.example.be_swp.Models.Appointments;
import com.example.be_swp.Models.ExpertOccupiedTimes;
import com.example.be_swp.Repository.AppointmentRepository;
import com.example.be_swp.Repository.ExpertOccupiedTimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertOccupiedTimeService {

    private final ExpertOccupiedTimeRepository _expertOccupiedTimeRepository;
    private final AppointmentRepository _appointmentRepository;

    public ExpertOccupiedTimeService(ExpertOccupiedTimeRepository _expertOccupiedTimeRepository, AppointmentRepository _appointmentRepository) {
        this._expertOccupiedTimeRepository = _expertOccupiedTimeRepository;
        this._appointmentRepository = _appointmentRepository;
    }

    public List<ExpertOccupiedTimesDTO> findAll(){
        List<ExpertOccupiedTimesDTO> expertOccupiedTimesDTOList = new ArrayList<>();
        List<ExpertOccupiedTimes> expertOccupiedTimesList = _expertOccupiedTimeRepository.findAll();

        if (!expertOccupiedTimesList.isEmpty()){
            for (ExpertOccupiedTimes occupiedTimes: expertOccupiedTimesList){
                ExpertOccupiedTimesDTO expertOccupiedTimesDTO = convertToDTO(occupiedTimes);
                expertOccupiedTimesDTOList.add(expertOccupiedTimesDTO);
            }
        }

        return expertOccupiedTimesDTOList;
    }

    public List<ExpertOccupiedTimesDTO> findByExpertId(int id){
        List<ExpertOccupiedTimesDTO> expertOccupiedTimesDTOList = new ArrayList<>();
        List<ExpertOccupiedTimes> expertOccupiedTimesList = _expertOccupiedTimeRepository.findByExpertId(id);

        if (!expertOccupiedTimesList.isEmpty()){
            for (ExpertOccupiedTimes occupiedTimes: expertOccupiedTimesList){
                ExpertOccupiedTimesDTO expertOccupiedTimesDTO = convertToDTO(occupiedTimes);
                expertOccupiedTimesDTOList.add(expertOccupiedTimesDTO);
            }
        }

        return expertOccupiedTimesDTOList;
    }

    public List<ExpertOccupiedTimesDTO> findAllToday(){
        List<ExpertOccupiedTimesDTO> expertOccupiedTimesDTOList = new ArrayList<>();
        List<ExpertOccupiedTimes> expertOccupiedTimesList = _expertOccupiedTimeRepository.findAllToday(LocalDate.now());

        if (!expertOccupiedTimesList.isEmpty()){
            for (ExpertOccupiedTimes occupiedTimes: expertOccupiedTimesList){
                ExpertOccupiedTimesDTO expertOccupiedTimesDTO = convertToDTO(occupiedTimes);
                expertOccupiedTimesDTOList.add(expertOccupiedTimesDTO);
            }
        }

        return expertOccupiedTimesDTOList;
    }

    public List<ExpertOccupiedTimesDTO> findAllTodayByExpertId(int id){
        List<ExpertOccupiedTimesDTO> expertOccupiedTimesDTOList = new ArrayList<>();
        List<ExpertOccupiedTimes> expertOccupiedTimesList = _expertOccupiedTimeRepository.findAllTodayByExpertId(LocalDate.now(),id);

        if (!expertOccupiedTimesList.isEmpty()){
            for (ExpertOccupiedTimes occupiedTimes: expertOccupiedTimesList){
                ExpertOccupiedTimesDTO expertOccupiedTimesDTO = convertToDTO(occupiedTimes);
                expertOccupiedTimesDTOList.add(expertOccupiedTimesDTO);
            }
        }

        return expertOccupiedTimesDTOList;
    }

    public ExpertOccupiedTimesDTO convertToDTO(ExpertOccupiedTimes occupiedTimes){
        LocalDateTime timeStart = LocalDateTime.of(occupiedTimes.getDate(),occupiedTimes.getStartAt());
        LocalDateTime timeEnd = LocalDateTime.of(occupiedTimes.getDate(),occupiedTimes.getEndAt());

        Optional<Appointments> optionalAppointments = _appointmentRepository.findByTime(timeStart,timeEnd);
        Appointments appointments = optionalAppointments.get();

        return new ExpertOccupiedTimesDTO(occupiedTimes.getExpertOccupiedTimeId(), occupiedTimes.getExperts().getExpertId(), appointments.getAppointmentId(), occupiedTimes.getExperts().getUsers().getFullName(), appointments.getServices().getServiceName(), appointments.getUsers().getFullName(), occupiedTimes.getDate(), occupiedTimes.getStartAt(), occupiedTimes.getEndAt(),occupiedTimes.getStatus(), occupiedTimes.getCreated_at(), occupiedTimes.getUpdated_at());
    }
}
