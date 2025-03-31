package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.Appointments.AppointmentsDTO;
import com.example.be_swp.DTOs.ExpertsDTO;
import com.example.be_swp.DTOs.Request.ExpertScheduleRequest;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.Appointments;
import com.example.be_swp.Models.ExpertOccupiedTimes;
import com.example.be_swp.Models.Experts;
import com.example.be_swp.Repository.AppointmentRepository;
import com.example.be_swp.Repository.ExpertOccupiedTimeRepository;
import com.example.be_swp.Service.ExpertOccupiedTimeService;
import com.example.be_swp.mapper.appointment.AppointmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/expert")
@AllArgsConstructor
public class ExpertOccupiedTimesController {

    private final ExpertOccupiedTimeService _expertOccupiedTimeService;
    private final AppointmentMapper appointmentMapper;

    private ExpertOccupiedTimeRepository _expertOccupiedTimeRepository;

    private AppointmentRepository appointmentRepository;

    @PostMapping("/availableTimes")
    public ApiResponse<?> available(@RequestBody ExpertScheduleRequest request){
        List<Long> availableTime = new ArrayList<>(List.of(8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L));

        List<ExpertOccupiedTimes> expertOccupiedTimesList = _expertOccupiedTimeRepository.findByExpertIdAndDate(request.getExpertId(),request.getDate())    ;
        if(expertOccupiedTimesList==null){
            return  new ApiResponse<>("200", availableTime, "success");
        }

//        List<Long> emptyList =  new ArrayList<>();

        Set<Long> occupiedTimes = expertOccupiedTimesList.stream()
                .flatMap(expertOccupiedTimes -> {
                    List<Long> tempList = new ArrayList<>();
                    Long arr = expertOccupiedTimes.getStartAt();
                    while (arr < expertOccupiedTimes.getEndAt()) {
                        tempList.add(arr);
                        arr++;
                    }
                    return tempList.stream();
                })
                .collect(Collectors.toCollection(LinkedHashSet::new)); // Đảm bảo khô

        availableTime.removeAll(occupiedTimes);



        return  new ApiResponse<>("200", availableTime, "success");
    }


    @PostMapping("/schedule")
    public ApiResponse<?> schedule(@RequestParam Long expertId){

        if(expertId==null){
            throw new NullPointerException("expertId is null");
        }

        List<ExpertsDTO> expertsDTOList = appointmentRepository.findAllByExpertId(expertId).stream()
                .map(appointment  ->{
                    ExpertsDTO epx= appointmentMapper.mapToExpertDTO(appointment);
                    return epx;
                })
                .toList();



        return  new ApiResponse<>("200", expertsDTOList, "success");


    }



}


