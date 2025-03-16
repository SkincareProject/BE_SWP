package com.example.be_swp.Service;

import com.example.be_swp.DTOs.ExpertOccupiedTimesDTO;
import com.example.be_swp.Models.ExpertOccupiedTimes;
import com.example.be_swp.Repository.ExpertOccupiedTimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertOccupiedTimeService {

    private final ExpertOccupiedTimeRepository _expertOccupiedTimeRepository;

    public ExpertOccupiedTimeService(ExpertOccupiedTimeRepository expertOccupiedTimeRepository) {
        _expertOccupiedTimeRepository = expertOccupiedTimeRepository;
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
        return new ExpertOccupiedTimesDTO(occupiedTimes.getExpertOccupiedTimeId(), occupiedTimes.getExperts().getExpertId(), occupiedTimes.getExperts().getUsers().getFullName(), occupiedTimes.getDate(), occupiedTimes.getStartAt(), occupiedTimes.getEndAt(),occupiedTimes.getStatus(), occupiedTimes.getCreated_at(), occupiedTimes.getUpdated_at());
    }
}
