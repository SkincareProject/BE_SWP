package com.example.be_swp.Service;

import com.example.be_swp.DTOs.WorkScheduleDTO;
import com.example.be_swp.Models.Experts;
import com.example.be_swp.Models.WorkSchedule;
import com.example.be_swp.Repository.ExpertRepository;
import com.example.be_swp.Repository.WorkScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkScheduleService {

    private final WorkScheduleRepository _workScheduleRepository;
    private final ExpertRepository _expertRepository;

    public WorkScheduleService(WorkScheduleRepository workScheduleRepository, ExpertRepository expertRepository){
        _workScheduleRepository = workScheduleRepository;
        _expertRepository = expertRepository;
    }

    public List<WorkScheduleDTO> findAll(){
        List<WorkSchedule> workScheduleList = _workScheduleRepository.findAll();
        List<WorkScheduleDTO> workScheduleDTOList = new ArrayList<>();

        if (!workScheduleList.isEmpty()){
            for (WorkSchedule ws: workScheduleList){
                WorkScheduleDTO workScheduleDTO = new WorkScheduleDTO(ws.getWorkScheduleId(), ws.getExperts().getExpertId(), ws.getStart_at(), ws.getEnd_at(), ws.getWork_date(), ws.getStatus(), ws.getCreated_at(), ws.getUpdated_at());
                workScheduleDTOList.add(workScheduleDTO);
            }
        }

        return workScheduleDTOList;
    }

    public WorkScheduleDTO add(WorkScheduleDTO workScheduleDTO, int expertId){

        Optional<Experts> optionalExperts = _expertRepository.findById(expertId);

        boolean is_exist = false;
        List<WorkSchedule> workScheduleList = _workScheduleRepository.findAll();
        for (WorkSchedule ws: workScheduleList){
            if (ws.getExperts().getExpertId() == expertId && ws.getStart_at().equals(workScheduleDTO.getStart_at()) && ws.getEnd_at().equals(workScheduleDTO.getEnd_at()) && ws.getWork_date().isEqual(workScheduleDTO.getWork_date())){
                is_exist = true;
            }
        }

        if(workScheduleDTO.getStart_at().isAfter(workScheduleDTO.getEnd_at()) || workScheduleDTO.getWork_date().isBefore(LocalDate.now().plusDays(1))){

            workScheduleDTO.setWorkScheduleId(-1);

        }else if(optionalExperts.isEmpty()){

            workScheduleDTO.setWorkScheduleId(-2);

        }else if (is_exist){

            workScheduleDTO.setWorkScheduleId(-3);

        }else{
            Experts experts = optionalExperts.get();
            WorkSchedule workSchedule = new WorkSchedule();
            workSchedule.setExperts(experts);
            workSchedule.setStart_at(workScheduleDTO.getStart_at());
            workSchedule.setEnd_at(workScheduleDTO.getEnd_at());
            workSchedule.setWork_date(workScheduleDTO.getWork_date());
            workSchedule.setStatus(1);
            workSchedule.setCreated_at(LocalDateTime.now());
            workSchedule.setUpdated_at(LocalDateTime.now());

            experts.getWorkScheduleList().add(workSchedule);
            _workScheduleRepository.save(workSchedule);

            workScheduleDTO.setWorkScheduleId(workSchedule.getWorkScheduleId());
            workScheduleDTO.setExpertId(workSchedule.getExperts().getExpertId());
            workScheduleDTO.setStatus(workSchedule.getStatus());
        }

        return workScheduleDTO;
    }

    public WorkScheduleDTO delete(int id){
        WorkScheduleDTO workScheduleDTO = new WorkScheduleDTO();

        Optional<WorkSchedule> optionalWorkSchedule = _workScheduleRepository.findById(id);

        if (optionalWorkSchedule.isEmpty()){

            workScheduleDTO.setWorkScheduleId(-1);

        }else {
            WorkSchedule workSchedule = optionalWorkSchedule.get();

            workScheduleDTO = new WorkScheduleDTO(workSchedule.getWorkScheduleId(),workSchedule.getExperts().getExpertId(),workSchedule.getStart_at(), workSchedule.getEnd_at(),workSchedule.getWork_date(),workSchedule.getStatus(), workSchedule.getCreated_at(),workSchedule.getUpdated_at());

            Optional<Experts> optionalExperts = _expertRepository.findById(workSchedule.getExperts().getExpertId());
            Experts experts = optionalExperts.get();

            experts.getWorkScheduleList().remove(workSchedule);
            experts.setUpdated_at(LocalDateTime.now());
            _workScheduleRepository.delete(workSchedule);

        }

        return workScheduleDTO;
    }

}
