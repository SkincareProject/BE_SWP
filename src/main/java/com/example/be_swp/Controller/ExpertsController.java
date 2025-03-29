package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.ExpertsDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.Experts;
import com.example.be_swp.Repository.ExpertRepository;
import com.example.be_swp.mapper.experts.ExpertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experts")
public class ExpertsController {

    @Autowired
    private ExpertRepository _expertRepository;
    @Autowired
    private ExpertMapper _expertMapper;



    @GetMapping("/findAll")
    public ApiResponse<?> findAll(){
        List<Experts> experts = _expertRepository.findAll();
        List<?> expertsDTOList=_expertMapper.mapToExperts(experts);


        String status = "";
        String message = "";

        return new ApiResponse<>(status, expertsDTOList, message);

    }

    @GetMapping("/findById/{id}")
    public ApiResponse<?> findById(@PathVariable Long id){
        Optional<Experts> expertsDTO = _expertRepository.findById(id);

        String status = "";
        String message = "";


        return new ApiResponse<>(status,expertsDTO,message);
    }



}
